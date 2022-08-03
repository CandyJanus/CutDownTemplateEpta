package data.scripts.utils;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import java.util.List;

public class seven_util_sysgen {
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
