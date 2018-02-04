/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.JSON;

import Models.Deposit;
import Models.Mine;
import Models.Road;
import com.google.gson.Gson;
import java.util.LinkedList;

/**
 *
 * @author darkd
 */
public class JsonSaveProcess
{

    JsonModel information;
    LinkedList<Mine> mines;

    public JsonSaveProcess()
    {
    }

    public JsonSaveProcess(LinkedList<Mine> mines, InformationMineJson generalInformation, MinersJson minersInfo)
    {
        this.information = new JsonModel();
        this.information.setInfoMinas(generalInformation);
        this.information.setInfoMineros(minersInfo);
        this.mines = mines;
    }

    public String save()
    {
        this.minesInformation();
        Gson gson = new Gson();
        String json = gson.toJson(this.information);
        return json;
        //Gson load = new Gson();
//        JsonModel m = load.fromJson(json, JsonModel.class);
//        System.out.println(m.getMinas().getFirst().getEntradaMina().getX());

    }

    public void minesInformation()
    {
        this.mines.stream().map((m) ->
        {
            MineJSON myMine = new MineJSON(m.getMetal(), m.getMaxMiners(), m.getAmountForDeposit(), m.getUnitAmount(), m.getTimeToExtract(), m.getUnitTimeToExtract(), m.getSpeed(), m.getUnitSpeed(), m.getCollectQuantity());
            myMine.setAlto(m.getMatrix().size());
            myMine.setAncho(m.getMatrix().getFirst().size());
            for (int i = 0; i < m.getMatrix().size(); i++)
            {
                for (int j = 0; j < m.getMatrix().get(i).size(); j++)
                {
                    if (m.getMatrix().get(i).get(j).getObject() instanceof Road)
                    {
                        Road r = (Road) m.getMatrix().get(i).get(j).getObject();
                        if (r.isEntry())
                            myMine.setEntradaMina(new MineEntrance(j, i));
                        myMine.getSeccionesMina().add(new SectionJson('T', j, i));
                    }
                    else if (m.getMatrix().get(i).get(j).getObject() instanceof Deposit)
                        myMine.getSeccionesMina().add(new SectionJson('D', j, i));
                }
            }
            return myMine;
        }).forEachOrdered((myMine) ->
        {
            this.information.getMinas().add(myMine);
        });
    }
}
