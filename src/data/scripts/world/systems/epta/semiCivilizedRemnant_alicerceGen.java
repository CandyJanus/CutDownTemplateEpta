package data.scripts.world.systems.epta;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.impl.campaign.fleets.FleetFactoryV3;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.FleetTypes;
import com.fs.starfarer.api.impl.campaign.ids.MemFlags;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.procgen.themes.RemnantOfficerGeneratorPlugin;
import com.fs.starfarer.api.util.Misc;

import java.util.Random;

public class semiCivilizedRemnant_alicerceGen {


    public void generate(SectorAPI sector) {

        StarSystemAPI system = sector.getStarSystem("Alicerce");
        PlanetAPI alicerce_star=system.getStar();

        system.addTag(Tags.THEME_REMNANT);
        //system.addTag(Tags.THEME_UNSAFE);


        //note: nexuses are fleets and not normal stations.

        CampaignFleetAPI nexusFleet = FleetFactoryV3.createEmptyFleet("semiCivilizedRemnants", FleetTypes.BATTLESTATION, null);
        nexusFleet.setCircularOrbitPointingDown(alicerce_star, 250f, 2240f ,260f);
        nexusFleet.setName("Nexus");
        FleetDataAPI nexusFleetData=nexusFleet.getFleetData();
        FleetMemberAPI nexus = Global.getFactory().createFleetMember(FleetMemberType.SHIP, "remnant_station2_Standard");
        nexus.updateStats();
        nexus.getRepairTracker().setCR(nexus.getRepairTracker().getMaxCR());
        String coreId = Commodities.ALPHA_CORE;
        AICoreOfficerPlugin plugin = Misc.getAICoreOfficerPlugin(coreId);
        Random random = new Random();
        PersonAPI nexusCaptain = plugin.createPerson(coreId, "semiCivilizedRemnants", random);
        //TODO think of better name
        //FullName nexusCaptainName=new FullName("Maroon","Unthinkable",FullName.Gender.MALE);
        FullName nexusCaptainName=new FullName("Old","Uncle",FullName.Gender.MALE);
        nexusCaptain.setName(nexusCaptainName);
        nexus.setCaptain(nexusCaptain);
        nexusFleetData.addFleetMember(nexus);
        nexusFleetData.setFlagship(nexus);
        nexusFleet.setCommander(nexusCaptain);
            //note: ...I don't know what this does
        RemnantOfficerGeneratorPlugin.integrateAndAdaptCoreForAIFleet(nexusFleet.getFlagship());
        RemnantOfficerGeneratorPlugin.addCommanderSkills(nexusCaptain, nexusFleet, null, 5, random);

        nexusFleet.clearAbilities();
        nexusFleet.setTransponderOn(true);
        nexusFleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_MAKE_AGGRESSIVE, true);
        nexusFleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_JUMP, true);
        nexusFleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_MAKE_ALLOW_DISENGAGE, true);
        nexusFleet.addTag(Tags.NEUTRINO_HIGH);
        nexusFleet.setStationMode(true);
        system.addEntity(nexusFleet);
        nexusFleet.setAI(null);

        //TODO: make my own interaction config and stuff

//        SectorEntityToken semiCivRemnantStation_alicerce = system.addCustomEntity("semiCivRemnantStation_alicerce", "Epta-Aligned Remnant Nexus", "station_hightech2", "semiCivilizedRemnants");






    }
}


//note: half sick, half mad, all lost.