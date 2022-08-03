package data.scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import data.scripts.util.MagicSettings;
import data.scripts.utils.seven_util_sysgen;
import data.scripts.world.sevencorpGenEpta;
import data.scripts.world.sevencorpGenTakeshido;
import exerelin.campaign.SectorManager;

import java.util.List;
import java.util.Map;

public class sevencorp_ModPlugin extends BaseModPlugin {

    private static org.apache.log4j.Logger log = Global.getLogger(sevencorp_ModPlugin.class);


    @Override
    public void onNewGame() {
        Map<String, Object> data = Global.getSector().getPersistentData();
        boolean isEptaEnabled=MagicSettings.getBoolean("seven_nexus","enableEptaFaction");
        boolean haveNexerelin = Global.getSettings().getModManager().isModEnabled("nexerelin");

        if (!haveNexerelin || SectorManager.getManager().isCorvusMode()) {
            if (isEptaEnabled) {
                new sevencorpGenEpta().generate(Global.getSector());
                data.put("epta_generated", "-");
            }
        }
    }


    @Override
    public void onGameLoad(boolean wasEnabledBefore) {
        boolean isEptaEnabled=MagicSettings.getBoolean("seven_nexus","enableEptaFaction");
        boolean haveNexerelin = Global.getSettings().getModManager().isModEnabled("nexerelin");

        Map<String, Object> data = Global.getSector().getPersistentData();

        StarSystemAPI ali_sys=Global.getSector().getStarSystem("Alicerce");       

        //note: put in a ~~second~~ third layer of protection to prevent future double-spawning
        //note: I'm pretty sure those duplicate layers of protection don't even work and only the memkey matters.
        if (!haveNexerelin || SectorManager.getManager().isCorvusMode()) {
            if (isEptaEnabled && !data.containsKey("epta_generated")) {
                new sevencorpGenEpta().generate(Global.getSector());
                data.put("epta_generated", "-");
            }
        }


        if (ali_sys!=null)
        {
            exploreAll(ali_sys);
        }


        Global.getSector().getFaction("sevencorp").clearShipRoleCache();
    }

   public static void exploreAll(StarSystemAPI system) {
        List<SectorEntityToken> allEntities = system.getAllEntities();
        for (SectorEntityToken entity : allEntities) {
            //note: just checking for entities doesn't stop the NPEs, I needed to check for markets in particular
            if (entity.getMarket()!=null){
                entity.getMarket().setSurveyLevel(MarketAPI.SurveyLevel.FULL);
            }
        }
    }
}