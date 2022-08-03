package data.scripts.world;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;
import data.scripts.world.systems.bushido.bushido_meiyoGen;

import data.scripts.world.systems.epta.sevencorp_alicerceGen;

public class sevencorpGen implements SectorGeneratorPlugin {
   
    @Override
    public void generate(SectorAPI sector) {
        
        new sevencorp_alicerceGen().generate(sector);

        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("sevencorp");
    }
}