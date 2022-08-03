package data.scripts.world.systems.epta;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.*;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.PlanetConditionGenerator;
import com.fs.starfarer.api.impl.campaign.procgen.StarAge;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;
import org.lazywizard.lazylib.campaign.orbits.EllipticalOrbit;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class sevencorp_alicerceGen {
  
    public void generate(SectorAPI sector) {

        StarSystemAPI system = sector.createStarSystem("Alicerce");
        system.getLocation().set(-6800, -7200);
        system.setBackgroundTextureFilename("graphics/backgrounds/background2.jpg");
        system.addTag(Tags.THEME_CORE_POPULATED);

        boolean haveUnknownSkies = Global.getSettings().getModManager().isModEnabled("US");
     // Star
        PlanetAPI alicerce_star = system.initStar(
                "sevencorp_alicerce",
                "star_orange",
                905f,
                701f);
        
        alicerce_star.setName("Alicerce");
        
        system.setLightColor(new Color(230, 200, 156));

     // Irradiated heated planet
        PlanetAPI alicerce_1 = system.addPlanet("sevencorp_lotus_fire",
        		alicerce_star,
                "Lotus Riven",
                "irradiated",
                140f, //starting angle
                140f, //size
                3100f, // orbit radius
                270f); // orbit time
        alicerce_1.setCustomDescriptionId("sevencorp_lotus_fire_planet");
        PlanetConditionGenerator.generateConditionsForPlanet(alicerce_1, StarAge.AVERAGE);
        //alicerce_1.getMarket().addCondition("decivilized"); //note: never leave a spacer behind
        alicerce_1.getMarket().addCondition(Conditions.DECIVILIZED);
//        if(haveUnknownSkies){
//            alicerce_1.getMarket().addCondition("US_tunnels");
//        }

     // Heated Army Source
        PlanetAPI alicerce_2 = system.addPlanet("sevencorp_epta_ignitus",
        		alicerce_star,
                "Epta Ignitus",
                "lava",
                200f, //starting angle
                140f, //size
                1650f, // orbit radius
                180f); // orbit time
        alicerce_2.setCustomDescriptionId("sevencorp_epta_ignitus_planet");  
        
        MarketAPI alicerce_2_market = sevencorp_AddMarketplace.addMarketplace("sevencorp",
                alicerce_2,
                null,
                "Epta Ignitus",
                6,
                new ArrayList<>(
                    Arrays.asList(
                        Conditions.POPULATION_6,
                        Conditions.TECTONIC_ACTIVITY,
                        Conditions.VERY_HOT,
                        Conditions.RARE_ORE_ULTRARICH,
                        Conditions.ORE_RICH,
                        Conditions.METEOR_IMPACTS,
                        Conditions.FREE_PORT
                    )
                ),
                new ArrayList<>(
                    Arrays.asList(
                        Submarkets.GENERIC_MILITARY,
                        Submarkets.SUBMARKET_OPEN,
                        Submarkets.SUBMARKET_STORAGE,
                        Submarkets.SUBMARKET_BLACK
                    )
                ),
                new ArrayList<>(
                    Arrays.asList(
                        Industries.POPULATION,
                        Industries.MEGAPORT,
                        Industries.REFINING,
                        Industries.MINING,
                        //Industries.REFINING,
                        Industries.STARFORTRESS_HIGH,
                        Industries.HEAVYBATTERIES,
                        Industries.HIGHCOMMAND,
                        Industries.ORBITALWORKS
                    )
                ),
                true,
                false);
        
        alicerce_2_market.getIndustry(Industries.MEGAPORT).setAICoreId(Commodities.BETA_CORE);
        alicerce_2_market.getIndustry(Industries.MEGAPORT).setSpecialItem(new SpecialItemData(Items.FULLERENE_SPOOL, null));
        alicerce_2_market.getIndustry(Industries.MINING).setAICoreId(Commodities.GAMMA_CORE);
        //alicerce_2_market.addIndustry(Industries.ORBITALWORKS,new ArrayList<String>(Arrays.asList(Items.CORRUPTED_NANOFORGE)));
        alicerce_2_market.addIndustry(Industries.ORBITALWORKS);
        alicerce_2_market.getIndustry(Industries.ORBITALWORKS).setSpecialItem(new SpecialItemData(Items.PRISTINE_NANOFORGE, null));
        alicerce_2_market.getIndustry(Industries.ORBITALWORKS).setAICoreId(Commodities.ALPHA_CORE);
        alicerce_2_market.getIndustry(Industries.REFINING).setAICoreId(Commodities.GAMMA_CORE);
        alicerce_2_market.getIndustry(Industries.STARFORTRESS_HIGH).setAICoreId(Commodities.ALPHA_CORE);
        alicerce_2_market.getIndustry(Industries.HEAVYBATTERIES).setAICoreId(Commodities.GAMMA_CORE);
        alicerce_2_market.getIndustry(Industries.HIGHCOMMAND).setAICoreId(Commodities.ALPHA_CORE);
        alicerce_2_market.getIndustry(Industries.HIGHCOMMAND).setSpecialItem(new SpecialItemData(Items.CRYOARITHMETIC_ENGINE, null));
        
     // Habitable ice cube housing -

        PlanetAPI  alicerce_3 = system.addPlanet("sevencorp_assiduis_frigore",
        		alicerce_star,
                "Assiduis Frigore",
                "tundra",
                100f, //starting angle
                143f, //size
                6100f, // orbit radius
                442f); // orbit time
        alicerce_3.setCustomDescriptionId("sevencorp_assiduis_frigore_kuzminki");  
        
        MarketAPI alicerce_3_market = sevencorp_AddMarketplace.addMarketplace("sevencorp",
                alicerce_3,
                null,
                "Assiduis Frigore",
                5,
                new ArrayList<>(
                    Arrays.asList(
                        Conditions.POPULATION_5,
                        Conditions.FARMLAND_POOR,
                        Conditions.HABITABLE,
                        Conditions.VOLATILES_DIFFUSE,
                        Conditions.FREE_PORT
                    )
                ),
                new ArrayList<>(
                    Arrays.asList(
                        Submarkets.SUBMARKET_OPEN,
                        Submarkets.SUBMARKET_STORAGE,
                        Submarkets.SUBMARKET_BLACK
                    )
                ),
                new ArrayList<>(
                    Arrays.asList(
                        Industries.POPULATION,
                        Industries.MEGAPORT,
                        Industries.MINING,
                        Industries.BATTLESTATION_HIGH,
                        Industries.GROUNDDEFENSES,
                        Industries.REFINING
                    )
                ),
                true,
                false);
        
        alicerce_3_market.getIndustry(Industries.MEGAPORT).setAICoreId(Commodities.GAMMA_CORE);
        alicerce_3_market.getIndustry(Industries.MINING).setAICoreId(Commodities.GAMMA_CORE);
        alicerce_3_market.getIndustry(Industries.BATTLESTATION_HIGH).setAICoreId(Commodities.GAMMA_CORE);
        alicerce_3_market.getIndustry(Industries.GROUNDDEFENSES).setAICoreId(Commodities.GAMMA_CORE);

//        if(haveUnknownSkies)
//        {
//            alicerce_3_market.addCondition("US_mind");
//        }

     // The Arid School of AIs, also capital
        PlanetAPI alicerce_4 = system.addPlanet("sevencorp_supra_directivum",
        		alicerce_star,
                "Supra Directivum",
                "arid",
                10f, //starting angle
                160f, //size
                4500f, // orbit radius
                497f); // orbit time
        alicerce_4.setCustomDescriptionId("sevencorp_supra_directivum_planet");  
        
        MarketAPI alicerce_4_market = sevencorp_AddMarketplace.addMarketplace("sevencorp",
                alicerce_4,
                null,
                "Supra Directivum",
                7,
                new ArrayList<>(
                    Arrays.asList(
                        Conditions.POPULATION_7,
                        Conditions.HABITABLE,
                        Conditions.MILD_CLIMATE,
                        Conditions.FARMLAND_ADEQUATE,
                        Conditions.ORGANICS_COMMON,
                        Conditions.FREE_PORT
                    )
                ),
                new ArrayList<>(
                    Arrays.asList(
                        Submarkets.SUBMARKET_OPEN,
                        Submarkets.SUBMARKET_STORAGE,
                        Submarkets.SUBMARKET_BLACK
                    )
                ),
                new ArrayList<>(
                    Arrays.asList(
                        Industries.POPULATION,
                        Industries.MEGAPORT,
                        Industries.FARMING,
                        Industries.MINING,
                        Industries.STARFORTRESS_HIGH,
                        Industries.HEAVYBATTERIES,
                        Industries.PATROLHQ,
                        Industries.LIGHTINDUSTRY
                    )
                ),
                true,
                false);
        
        alicerce_4_market.getIndustry(Industries.POPULATION).setAICoreId(Commodities.GAMMA_CORE);
        alicerce_4_market.getIndustry(Industries.MEGAPORT).setAICoreId(Commodities.GAMMA_CORE);
        alicerce_4_market.getIndustry(Industries.FARMING).setAICoreId(Commodities.GAMMA_CORE);
        alicerce_4_market.getIndustry(Industries.MINING).setAICoreId(Commodities.GAMMA_CORE);
        alicerce_4_market.getIndustry(Industries.STARFORTRESS_HIGH).setAICoreId(Commodities.ALPHA_CORE);
        alicerce_4_market.addIndustry(Industries.ORBITALWORKS);
        alicerce_4_market.getIndustry(Industries.ORBITALWORKS).setSpecialItem(new SpecialItemData(Items.CORRUPTED_NANOFORGE, null));
        alicerce_4_market.getIndustry(Industries.ORBITALWORKS).setAICoreId(Commodities.BETA_CORE);
        alicerce_4_market.getIndustry(Industries.HEAVYBATTERIES).setAICoreId(Commodities.GAMMA_CORE);
        alicerce_4_market.getIndustry(Industries.LIGHTINDUSTRY).setAICoreId(Commodities.BETA_CORE);

     // Seven Corporation Trade Station
        SectorEntityToken seven_enterprises = system.addCustomEntity("alicerce_seven_enterprises", "Seven Enterprises", "station_hightech3", "sevencorp");
        seven_enterprises.setCircularOrbitPointingDown(alicerce_star, 210, 5000f ,260f);
        seven_enterprises.setCustomDescriptionId("alicerce_seven_enterprises_station");
        
		MarketAPI alicerce_seven_enterprises_station = sevencorp_AddMarketplace.addMarketplace("sevencorp",
				seven_enterprises, null,
                "Seven Enterprises",
                5,
                new ArrayList<>(
                        Arrays.asList(
                                Conditions.POPULATION_5,
                                Conditions.NO_ATMOSPHERE,
                                Conditions.OUTPOST,
                                Conditions.FREE_PORT
                        )
                ),
                new ArrayList<>(
                        Arrays.asList(
                                Submarkets.SUBMARKET_OPEN,
                                Submarkets.GENERIC_MILITARY,
                                Submarkets.SUBMARKET_STORAGE,
                                Submarkets.SUBMARKET_BLACK
                        )
                ),
                new ArrayList<>(
                        Arrays.asList(
                                Industries.POPULATION,
                                Industries.MEGAPORT,
                                Industries.LIGHTINDUSTRY,
                                Industries.WAYSTATION,
                                Industries.MILITARYBASE,
                                Industries.STARFORTRESS_HIGH
                        )
                ),
                true,
                false);
        
		alicerce_seven_enterprises_station.getIndustry(Industries.LIGHTINDUSTRY).setAICoreId(Commodities.GAMMA_CORE);
		alicerce_seven_enterprises_station.getIndustry(Industries.STARFORTRESS_HIGH).setAICoreId(Commodities.ALPHA_CORE);
        alicerce_seven_enterprises_station.getIndustry(Industries.MEGAPORT).setAICoreId(Commodities.BETA_CORE);
        alicerce_seven_enterprises_station.getIndustry(Industries.MEGAPORT).setSpecialItem(new SpecialItemData(Items.FULLERENE_SPOOL, null));

		PlanetAPI   alicerce_5 = system.addPlanet("alicerce_rusty_moon",
				alicerce_star,
				"Rusty Moon",
				"barren_venuslike",
				250f, //starting angle
				90f, //size
				7600f, // orbit radius
				625f); // orbit time
		alicerce_5.setCustomDescriptionId("alicerce_rusty_moon_planet");
        PlanetConditionGenerator.generateConditionsForPlanet(alicerce_5, StarAge.AVERAGE);
            
     // Distant frozen rock, went so far for habitation  // note: ye old incest fridge is causing me grief

        PlanetAPI alicerce_7 = system.addPlanet("sevencorp_Incessant_freezing",
        		alicerce_star,
                "Incessant Freezing",
                "frozen2",
                140f, //starting angle
                140f, //size
                11000f, // orbit radius
                600f); // orbit time
        //EllipticalOrbit(SectorEntityToken focus, float startAngle, float orbitWidth, float orbitHeight, float orbitAngle, float daysPerOrbit)
        //note: let's make the orbit deliberately too fast because it's funny, also something something volatile gravitic destabilization
        OrbitAPI IncFrezOrbit= new EllipticalOrbit(alicerce_star, 0, 6400, 15000f, 60, 365f);
        alicerce_7.setOrbit(IncFrezOrbit);

        alicerce_7.setCustomDescriptionId("sevencorp_Incessant_freezing_planet");

                MarketAPI alicerce_7_market = sevencorp_AddMarketplace.addMarketplace("pirates",
                alicerce_7,
                null,
                "Incessant Freezing",
                4,
                new ArrayList<>(
                    Arrays.asList(
                        Conditions.POPULATION_4,
                        Conditions.HIGH_GRAVITY,
                        Conditions.VERY_COLD,
                        Conditions.VOLATILES_DIFFUSE,
                        Conditions.FREE_PORT,
                        "ruins_widespread"
                    )
                ),
                new ArrayList<>(
                    Arrays.asList(
                        Submarkets.SUBMARKET_OPEN,
                        Submarkets.SUBMARKET_STORAGE,
                        Submarkets.SUBMARKET_BLACK
                    )
                ),
                new ArrayList<>(
                    Arrays.asList(
                        Industries.POPULATION,
                        Industries.SPACEPORT,
                        Industries.MINING,
                        Industries.WAYSTATION,
                        Industries.PATROLHQ,
                        Industries.GROUNDDEFENSES
                    )
                ),
                true,
                false);

     // Another commercial center, but for buff tritachyon
        SectorEntityToken tritachyon_seven = system.addCustomEntity("alicerce_tritachyon_seven", "Tri Alliance Commercial", "station_hightech3", "sevencorp");
        tritachyon_seven.setCircularOrbitPointingDown(alicerce_star, 300, 5000f ,260f);
        tritachyon_seven.setCustomDescriptionId("alicerce_tritachyon_seven_station");
        
		MarketAPI tritachyon_seven_market = sevencorp_AddMarketplace.addMarketplace("tritachyon",
				tritachyon_seven, null,
                "Tri Alliance Commercial",
                4,
                new ArrayList<>(
                        Arrays.asList(
                                //Conditions.POPULATION_6,
                                Conditions.NO_ATMOSPHERE,
                                Conditions.OUTPOST,
                                Conditions.FREE_PORT
                        )
                ),
                new ArrayList<>(
                        Arrays.asList(
                                Submarkets.SUBMARKET_OPEN,
                                Submarkets.SUBMARKET_STORAGE,
                                Submarkets.SUBMARKET_BLACK
                        )
                ),
                new ArrayList<>(
                        Arrays.asList(
                                Industries.POPULATION,
                                Industries.MEGAPORT,
                                //Industries.LIGHTINDUSTRY,
                                Industries.FUELPROD,
                                Industries.MILITARYBASE,
                                Industries.STARFORTRESS_HIGH,
                                Industries.REFINING
                        )
                ),
                true,
                false);
        
		//tritachyon_seven_market.getIndustry(Industries.LIGHTINDUSTRY).setAICoreId(Commodities.GAMMA_CORE);
		tritachyon_seven_market.getIndustry(Industries.STARFORTRESS_HIGH).setAICoreId(Commodities.GAMMA_CORE);
        tritachyon_seven_market.getIndustry(Industries.FUELPROD).setAICoreId(Commodities.BETA_CORE);
        tritachyon_seven_market.getIndustry(Industries.FUELPROD).setSpecialItem(new SpecialItemData(Items.SYNCHROTRON, null));

	 // Relay/Buoy/Array 
        SectorEntityToken alicerce_relay = system.addCustomEntity("sevencorp_alicerce_relay", // unique id
                "Gia's Silver Tongue (Comm Relay)", // name - if null, defaultName from custom_entities.json will be used
                "comm_relay", // type of object, defined in custom_entities.json
                "sevencorp"); // faction
        alicerce_relay.setCircularOrbitPointingDown( alicerce_star, 31f, 5900, 370);
        
        SectorEntityToken alicerce_buoy = system.addCustomEntity("sevencorp_alicerce_buoy", // unique id
                "Gia's Gentle Hands (Nav Buoy)", // name - if null, defaultName from custom_entities.json will be used
                "nav_buoy", // type of object, defined in custom_entities.json
                "sevencorp"); // faction
        alicerce_buoy.setCircularOrbitPointingDown( alicerce_star, 151f, 7290, 370);
        
        SectorEntityToken alicerce_array = system.addCustomEntity("sevencorp_alicerce_array", // unique id
                "Gia's Piercing Eyes (Sensor Array)", // name - if null, defaultName from custom_entities.json will be used
                "sensor_array", // type of object, defined in custom_entities.json
                "sevencorp"); // faction
        alicerce_array.setCircularOrbitPointingDown( alicerce_star, 271f, 7240, 370);

            // Inner system jump point
        JumpPointAPI jumpPoint1 = Global.getFactory().createJumpPoint("sevencorp_alicerce_inner_jump", "Alicerce, Ignitus Jump Point");
        jumpPoint1.setCircularOrbit(alicerce_star, 134, 2720, 213);
        
        system.addEntity(jumpPoint1);

        // Outer system jump point
        JumpPointAPI jumpPoint2 = Global.getFactory().createJumpPoint("sevencorp_alicerce_fringe_jump", "Alicerce, Frigore Jump Point");
        jumpPoint2.setCircularOrbit(alicerce_star, 140, 4020, 403);

        system.addEntity(jumpPoint2);
        
     // ring for Ignitus/Alicia's station
        system.addRingBand(alicerce_star, "misc", "rings_dust0", 256f, 0, Color.white, 256f, 2120, 80f);
	system.addRingBand(alicerce_star, "misc", "rings_dust0", 256f, 1, Color.white, 256f, 2320, 100f);
	system.addRingBand(alicerce_star, "misc", "rings_dust0", 256f, 2, Color.white, 256f, 2520, 130f);
	system.addRingBand(alicerce_star, "misc", "rings_dust0", 256f, 1, Color.white, 256f, 2720, 80f);
        system.addRingBand(alicerce_star, "misc", "rings_dust0", 256f, 1, Color.white, 256f, 3750, 305f, null, null);
	system.addRingBand(alicerce_star, "misc", "rings_asteroids0", 256f, 1, Color.white, 256f, 3850, 295f, null, null);
        system.addAsteroidBelt(alicerce_star, 73, 2120, 165, 479, 100, Terrain.ASTEROID_BELT, "Asteroid Belt");
        system.addAsteroidBelt(alicerce_star, 101, 2156, 165, 483, 110, Terrain.ASTEROID_BELT, "Asteroid Belt");
        system.addAsteroidBelt(alicerce_star, 202, 3856, 305, 483, 290, Terrain.ASTEROID_BELT, "Asteroid Belt");

        //note: the necessary information for this hasn't been implemented yet, it will just NPE your ass if you uncomment it

            //note: ...I know I wrote this, but is it tonally appropriate for the relatively serious tone we're going with for the Epta? I guess I'll have to see.

        //note: alicia isn't the owner. she's implied to be dead. the place is run by her husband, George Spalding, who is apolitical, isolated, and just making it day by day.

//        SectorEntityToken indieStation_alicerce = system.addCustomEntity("indieStation_alicerce", "Ruined Orbital Manufactory", "station_side03", "independent");
//        indieStation_alicerce.setCircularOrbitPointingDown(alicerce_star, 0f, 2240f ,260f);
//            //note: don't set free floating stations as orbital stations, it CTDs
//        MarketAPI indieStation_alicerce_market = sevencorp_AddMarketplace.addMarketplace("independent",
//                indieStation_alicerce, null,
//                "Alicia's Stop-And-Shop",
//                2,
//                new ArrayList<>(
//                        Arrays.asList(
//                                //Conditions.POPULATION_6,
//                                Conditions.NO_ATMOSPHERE,
//                                Conditions.OUTPOST,
//                                Conditions.FREE_PORT
//                        )
//                ),
//                new ArrayList<>(
//                        Arrays.asList(
//                                Submarkets.SUBMARKET_OPEN,
//                                Submarkets.SUBMARKET_STORAGE,
//                                Submarkets.SUBMARKET_BLACK
//                        )
//                ),
//                new ArrayList<>(
//                        Arrays.asList(
//                                  Industries.POPULATION,
//                                  Industries.GROUNDDEFENSES,
//                                  Industries.SPACEPORT
////                                Industries.MEGAPORT,
////                                //Industries.LIGHTINDUSTRY,
////                                //Industries.FUELPROD,
////                                Industries.MILITARYBASE,
////                                Industries.STARFORTRESS_HIGH,
////                                Industries.REFINING
//                        )
//                ),
//                true,
//                false);
//
//        //note: It's not secret, just a bit hard to notice. Alicia needs to advertise better.
//        //note: setting hidden causes perma shortage, whoops
//        //indieStation_alicerce_market.setHidden(true);
//        indieStation_alicerce.setCustomDescriptionId("sevencorp_alicia_station");

        //note: program a custom building and add an "Epta Mech School" to zeta computers. for fresh faced young kids.
        

        SectorEntityToken zetaComputers_alicerce = system.addCustomEntity("zetaComputers_alicerce", "Zeta Computers", "station_side07", "sevencorp");
        zetaComputers_alicerce.setCircularOrbitPointingDown(alicerce_star, 120f, 2240f ,260f);
        //note: don't set free floating stations as orbital stations, it CTDs
        MarketAPI zetaComputers_alicerce_market = sevencorp_AddMarketplace.addMarketplace("sevencorp",
                zetaComputers_alicerce, null,
                "Zeta Computers",
                4,
                new ArrayList<>(
                        Arrays.asList(
                                Conditions.NO_ATMOSPHERE,
                                Conditions.OUTPOST,
                                Conditions.FREE_PORT
                        )
                ),
                new ArrayList<>(
                        Arrays.asList(
                                Submarkets.SUBMARKET_OPEN,
                                Submarkets.SUBMARKET_STORAGE,
                                Submarkets.SUBMARKET_BLACK
                        )
                ), //note: imma be real I don't know what industries suit a computer manufacturer.
                new ArrayList<>(
                        Arrays.asList(
                                Industries.POPULATION,
                                Industries.GROUNDDEFENSES,
                                Industries.SPACEPORT,
                                Industries.ORBITALSTATION_HIGH,
//                                Industries.MEGAPORT,
                                Industries.LIGHTINDUSTRY
//                                //Industries.FUELPROD,
//                                Industries.MILITARYBASE,
//                                Industries.STARFORTRESS_HIGH,
//                                Industries.REFINING
                        )
                ),
                true,
                false);


        // generates hyperspace destinations for in-system jump points
		system.autogenerateHyperspaceJumpPoints(true, false);


        //note: TODO when I remerge this with master, add a custom description to the beacon

        //note: ...if I have more than one beacon, the second one is assigned to a nearby system. Just the second one, though.

//        CustomCampaignEntityAPI beacon1=RemnantThemeGenerator.addBeacon(system, RESURGENT);
//        CustomCampaignEntityAPI beacon2=RemnantThemeGenerator.addBeacon(system, RESURGENT);
//        CustomCampaignEntityAPI beacon3=RemnantThemeGenerator.addBeacon(system, RESURGENT);
//        //note: the epta have tampered with this Luddic-left beacon to turn it blue, and keep it around as a mark of pride.
////        Color glowColor = new Color(50,35,200,255);
////        Color pingColor = new Color(50,35,200,255);
////        Color pingColor = new Color(255,125,250,255);
////        Misc.setWarningBeaconColors(beacon, glowColor, pingColor);
//
//        //note: literally did nothing
////        system.removeEntity(beacon1);
////        system.removeEntity(beacon2);
////        system.removeEntity(beacon3);
//
//        //note: the blue looks shit and weak, make it white instead?
//        Color glowColor = new Color(204,255,255,255);
//        Color pingColor = new Color(204,255,255,255);
//        //note: not settled on if I really want to keep them all the same color, or if I want to leave one of them untampered red to imply new attempts at warning. for now leave them all white, implies that the epta are on top of things and in control of their own space.
//        Misc.setWarningBeaconColors(beacon1, glowColor, pingColor);
//        Misc.setWarningBeaconColors(beacon2, glowColor, pingColor);
//        Misc.setWarningBeaconColors(beacon3, glowColor, pingColor);
//
//        List<SectorEntityToken> beaconList= new ArrayList<SectorEntityToken>();
//        beaconList.add(beacon1);
//        beaconList.add(beacon2);
//        beaconList.add(beacon3);
//
//        //note: wait, this is an interface. how am I declaring an interface as an object?
//        IntelManagerAPI intelManager=Global.getSector().getIntelManager();
//        List<IntelInfoPlugin> listOfWarningBeacons= intelManager.getIntel(WarningBeaconIntel.class);
//        //note: got help from histidine, who mentioned that beaconintel doesn't actually use the map, so you can pass in null.
//        //note: there's no doubt in my mind it would be a better idea to simply create my own custom entity and intel instead of using warning beacon methods.But I want to test the limits of my power.
//        for (IntelInfoPlugin intel: listOfWarningBeacons){
//            SectorEntityToken intelBeacon=intel.getMapLocation(null);
//            for(SectorEntityToken beacon:beaconList){
//                intelManager.removeIntel(intel);
//                //note: create a BETTER intel
//                //intelManager.addIntel();
//                //note: register a campaign level script to keep changing up the beacon ads
//            }
//        }
//            //note: let's remove all the intel matching these beacons, so we can replace them with our own. I dunno if this'll stick or if they'll regen.
////        for (IntelInfoPlugin intel: listOfWarningBeacons){
////            intel.getMapLocation(map);
////        }

        //TODO: dude, I have an idea. rent out a warning beacon and use it as a personal ad beacon to increase your rep with cash.

        //note: pasting random examples

//        ReliefFleetIntelAlt intel = ReliefFleetIntelAlt.createEvent(source, target);
//        setFP(intel.calcFP());
//        dialog.getTextPanel().addPara(getString("fleetSpawnMessage"));
//        Global.getSector().getIntelManager().addIntelToTextPanel(intel, dialog.getTextPanel());

        // Finally cleans up hyperspace
        cleanup(system);
	}

    //Shorthand function for cleaning up hyperspace
    private void cleanup(StarSystemAPI system){
    	HyperspaceTerrainPlugin plugin = (HyperspaceTerrainPlugin) Misc.getHyperspaceTerrain().getPlugin();
        NebulaEditor editor = new NebulaEditor(plugin);
        float minRadius = plugin.getTileSize() * 2f;
        float radius = system.getMaxRadiusInHyperspace();
        
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius * 0.5f, 0f, 360f);
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0f, 360f, 0.25f);
    }
}
