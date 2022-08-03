package data.scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.campaign.events.OfficerManagerEvent;
import com.fs.starfarer.api.util.Misc;

import java.util.List;

//import exerelin.campaign.SectorManager;

public class seven_AIMod_Utils extends BaseModPlugin {
    private static org.apache.log4j.Logger log = Global.getLogger(seven_AIMod_Utils.class);

    public static float shieldpowerdecaypercent = 0.0007f;
    public static float shieldpowerdecayflat = 1.2f;

    public static boolean playerHasCommodity(String id)
    {
        CampaignFleetAPI playerFleet = Global.getSector().getPlayerFleet();
        if (playerFleet == null)
            return false;
        List<CargoStackAPI> playerCargoStacks = playerFleet.getCargo().getStacksCopy();

        for (CargoStackAPI cargoStack : playerCargoStacks) {
            if (cargoStack.isCommodityStack() && cargoStack.getCommodityId().equals(id) && cargoStack.getSize() > 0) {
                return true;
            }
        }

        return false;
    }

    public static void removePlayerCommodity(String id)
    {
        CampaignFleetAPI playerFleet = Global.getSector().getPlayerFleet();
        if (playerFleet == null)
            return;
        List<CargoStackAPI> playerCargoStacks = playerFleet.getCargo().getStacksCopy();

        for (CargoStackAPI cargoStack : playerCargoStacks) {
            if (cargoStack.isCommodityStack() && cargoStack.getCommodityId().equals(id)) {
                cargoStack.subtract(1);
                if (cargoStack.getSize() <= 0)
                    playerFleet.getCargo().removeStack(cargoStack);
                return;
            }
        }
    }

    public static void addPlayerCommodity(String commodityId, int amount)
    {
        CampaignFleetAPI playerFleet = Global.getSector().getPlayerFleet();
        if (playerFleet == null)
            return;
        CargoAPI playerFleetCargo = playerFleet.getCargo();
        playerFleetCargo.addCommodity(commodityId, amount);
    }

    public static void createAIPersona(PersonAPI commander, ShipAPI ship)
    {
        PersonAPI AI;
        String AIName;
        if(Global.getSector().getPersistentData().get("seven_AIPersona_"+ship.getCaptain().getId()) instanceof PersonAPI) {
            AI = (PersonAPI)Global.getSector().getPersistentData().get("seven_AIPersona_"+ship.getCaptain().getId());
            AIName = (String)Global.getSector().getPersistentData().get("seven_AIPersona_"+"name_"+ship.getCaptain().getId());
        }
        else{
            AI = Misc.getAICoreOfficerPlugin("alpha_core").createPerson("alpha_core", "player", Misc.random);
            AIName = OfficerManagerEvent.createOfficer(Global.getSector().getFaction("remnant"),1, true).getName().getFullName();
            Global.getSector().getPersistentData().put("seven_AIPersona_"+ship.getCaptain().getId(),AI);
            Global.getSector().getPersistentData().put("seven_AIPersona_"+"name_"+ship.getCaptain().getId(),AIName);
        }
    }

    public static void createDuo(PersonAPI commander)
    {
        String duoName = getDuoName();
        Global.getSector().getPersistentData().put("AICore_DuoName_"+commander.getId(),duoName);
    }

    public static boolean hasDuo(PersonAPI commander)
    {
        return Global.getSector().getPersistentData().get("AICore_DuoName_"+commander.getId()) instanceof String;
    }

    public static void createAnanke(PersonAPI commander, ShipAPI ship)
    {
        PersonAPI AI;
        String AIName;
        if(Global.getSector().getPersistentData().get("seven_Ananke_"+ship.getCaptain().getId()) instanceof PersonAPI) {
            AI = (PersonAPI)Global.getSector().getPersistentData().get("seven_Ananke_"+ship.getCaptain().getId());
            AIName = (String)Global.getSector().getPersistentData().get("seven_Ananke_"+"name_"+ship.getCaptain().getId());
        }
        else{
            AI = Misc.getAICoreOfficerPlugin("omega_core").createPerson("omega_core", "player", Misc.random);
            AIName = "Ananke";
            Global.getSector().getPersistentData().put("seven_Ananke_"+ship.getCaptain().getId(),AI);
            Global.getSector().getPersistentData().put("seven_Ananke_"+"name_"+ship.getCaptain().getId(),AIName);
        }
    }

    public static void createAnankeDuo(PersonAPI commander)
    {
        String duoName = getDuoName();
        Global.getSector().getPersistentData().put("Ananke_DuoName_"+commander.getId(),duoName);
    }

    public static boolean hasAnankeDuo(PersonAPI commander)
    {
        return Global.getSector().getPersistentData().get("Ananke_DuoName_"+commander.getId()) instanceof String;
    }

    public static String getDuoName()
    {
        return "dynamic duo";
    }


}