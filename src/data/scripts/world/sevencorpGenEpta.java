package data.scripts.world;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;

import data.scripts.world.systems.epta.sevencorp_alicerceGen;

//note: rules of thumb- at least plus +0.1ish points for each tick on the checklist of using ai without enslaving them, treating AI decently, being big on cooperation, and being capitalist. Being anti-hege gives a lot of bonus points and leeway for...questionable behavior, given the desperate situation of the Epta. The Epta will sometimes ally with questionable groups out of realpolitik, and sometimes out of naivety. Also, being anti-AI is probably hostile.
//note: we're still roughing things out and put our best guesses for now. we'll need to do consultation with the mod authors involved to finalize things. that's been relatively low priority because we still have a lot of lore-work to add.

public class sevencorpGenEpta implements SectorGeneratorPlugin {
   
    @Override
    public void generate(SectorAPI sector) {

        new sevencorp_alicerceGen().generate(sector);

        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("sevencorp");

        FactionAPI sevencorp = sector.getFaction("sevencorp");
        FactionAPI player = sector.getFaction(Factions.PLAYER);
        FactionAPI hegemony = sector.getFaction(Factions.HEGEMONY);
        FactionAPI tritachyon = sector.getFaction(Factions.TRITACHYON);
        FactionAPI pirates = sector.getFaction(Factions.PIRATES);
        FactionAPI independent = sector.getFaction(Factions.INDEPENDENT);
        FactionAPI church = sector.getFaction(Factions.LUDDIC_CHURCH);
        FactionAPI path = sector.getFaction(Factions.LUDDIC_PATH);
        FactionAPI diktat = sector.getFaction(Factions.DIKTAT);
        FactionAPI kol = sector.getFaction(Factions.KOL);
        FactionAPI persean = sector.getFaction(Factions.PERSEAN);
        FactionAPI guard = sector.getFaction(Factions.LIONS_GUARD);
        FactionAPI remnant = sector.getFaction(Factions.REMNANTS);
        FactionAPI derelict = sector.getFaction(Factions.DERELICT);

        //vanilla factions
        //note: getID stopped working after I started calling the gen function from an if chain?
//        sevencorp.setRelationship(hegemony.getId(), -1.0f);
//        sevencorp.setRelationship(player.getId(), 0);
//        sevencorp.setRelationship(pirates.getId(), -0.5f);
//
//        sevencorp.setRelationship(independent.getId(), 0.5f);
//
//        sevencorp.setRelationship(tritachyon.getId(), 0.5f);
//
//        sevencorp.setRelationship(kol.getId(), -0.5f);
//        sevencorp.setRelationship(path.getId(), -0.75f);
//        sevencorp.setRelationship(church.getId(), -0.75f);
//
//        sevencorp.setRelationship(persean.getId(), 0.25f);
//        sevencorp.setRelationship(guard.getId(), 0.25f);
//        sevencorp.setRelationship(diktat.getId(), 0.25f);

        sevencorp.setRelationship(Factions.HEGEMONY, -1.0f);
        sevencorp.setRelationship(Factions.PLAYER, 0f);
        sevencorp.setRelationship(Factions.PIRATES, -0.5f);
        sevencorp.setRelationship(Factions.INDEPENDENT, 0.5f);
        sevencorp.setRelationship(Factions.TRITACHYON, 0.5f); //kinda sus, but TT has to smile with their children in the end, despite them being de facto deserters. the hege cometh
        sevencorp.setRelationship(Factions.KOL, -0.5f);
        sevencorp.setRelationship(Factions.LUDDIC_PATH, -0.75f);
        sevencorp.setRelationship(Factions.LUDDIC_CHURCH, -0.75f);
        sevencorp.setRelationship(Factions.PERSEAN, -0.35f);
        sevencorp.setRelationship(Factions.LIONS_GUARD,  0.25f);
        sevencorp.setRelationship(Factions.DIKTAT, -0.25f);

//        //environment
//        sevencorp.setRelationship(Factions.REMNANTS, RepLevel.HOSTILE);
//        sevencorp.setRelationship(Factions.DERELICT, RepLevel.HOSTILE);

        //environment
        sevencorp.setRelationship(remnant.getId(), RepLevel.COOPERATIVE);
        sevencorp.setRelationship(derelict.getId(), RepLevel.COOPERATIVE);

        // mod factions
            // hege-aligned
        sevencorp.setRelationship("vic", 0f); //just another amoral corp, which Epta is neutral about
        sevencorp.setRelationship("ironsentinel", -1.0f); //these look hege, so probably should be hostile
        sevencorp.setRelationship("ironshell", -1.0f);

            // hege-haters

        sevencorp.setRelationship("kadur_remnant", 0.50f);
        sevencorp.setRelationship("warhawk_republic", 0.50f);

            // luddic-aligned

        sevencorp.setRelationship("ashen_keepers", -0.5f); //note: still hate AI

            // corporations

        sevencorp.setRelationship("osiris", RepLevel.COOPERATIVE); //note: osiris is basically ancap, Epta loves that shit
        sevencorp.setRelationship("almighty_dollar", 0.1f); //note: capitalism is nice, but sevens pointed out that they're hege linked
        sevencorp.setRelationship("tiandong", 0.25f);
        sevencorp.setRelationship("blackrock_driveyards", 0.25f);
        sevencorp.setRelationship("HMI", 0.25f);
        sevencorp.setRelationship("gmda", -0.25f); //note: awaiting consultation with Fonzo to see what he thinks.
        sevencorp.setRelationship("gmda_patrol", -0.25f);

             // indie-adjacent

        sevencorp.setRelationship("unitedpamed", 0.45f); //note: basically indies
        sevencorp.setRelationship("al_ars", 0.15f); //note: after discussion, we've decided that Epta respect the indie merc hustle. They use mercs liberally themselves.

        // "socialist corporations"
                // Query: Revisiting faction relations, how should Epta feel about "socialist corporations" like Apex and Shadowyards? basically these are corporations owned by workers who are also shareholders
                // Answer: Hmm, not enemies, but epta not agrees with their vision point, but can negociate. Everyone have liberty to do what wants and live in peace

        sevencorp.setRelationship("apex_design", 0.2f); //note: I find it such a shame that they're fated to go to war because of their different stances on TT and the Hege. Really, both of them would rather be free of them all, and Apex covertly gives AI human rights. Different labels, different sides of the realpolitk blocs...
             //note: secretly it's all about the flat ships
        sevencorp.setRelationship("shadow_industry", 0.2f);

              // gemerally okay countries
        sevencorp.setRelationship("SCY", 0.25f);
        sevencorp.setRelationship("dassault_mikoyan", 0.25f);
        sevencorp.setRelationship("ORA", 0.25f); //note: pretty sure Epta has no clue that they're mind controlling people

              // AI factions

        sevencorp.setRelationship("hcok", 0.3f); //note: kemet is xenophobic but also AI using?
        sevencorp.setRelationship("hcok_pharaoh_guard", 0.3f);
        sevencorp.setRelationship("science_fuckers", RepLevel.FRIENDLY); //note: will turn hostile after ascension, which is fine
        sevencorp.setRelationship("mayorate", RepLevel.COOPERATIVE); //note: epta is probably not aware of the mayorate's war crimes and just thinks of them as fellow AI just trying to survive
        sevencorp.setRelationship("ocua", RepLevel.COOPERATIVE); //note: weirdchamp cookie obsession, but ok
        sevencorp.setRelationship("interstellarimperium", 0.40f); //note: rather authoritarian, but TT is pretty sus and Epta makes common ground with them too
            //note: "Unless if Imperium tortures stuff, Epta approves allies that likes AIs, and not matters so much if the government is imperium, everyone decides what wants for their lifes"
			//note: being naive, I wonder if Epta buys into the surface appearance of Imperium being a nice place.
        sevencorp.setRelationship("xhanempire", -0.5f);
            //note: Epta is willing to stomach a lot of horrible things, but turning settlements into protein paste seems to be beyond their breaking point. By order of Sevens.
        sevencorp.setRelationship("blade_breakers", -0.5f); //note: probably malding pretty hard at bad representation
        sevencorp.setRelationship("pn_colony", 0.2f); //note: apparently covertly like AI and give them rights. If the Hege is out of the picture, might become friends.

            //generic "evil"

        sevencorp.setRelationship("diableavionics", -0.7f);
        sevencorp.setRelationship("tahlan_legioinfernalis", -0.50f);
        sevencorp.setRelationship("cabal", -0.5f);
        sevencorp.setRelationship("roider", -0.6f);
        sevencorp.setRelationship("exipirated", -0.6f);
        sevencorp.setRelationship("draco", -0.6f);
        sevencorp.setRelationship("fang", -0.6f);
        sevencorp.setRelationship("junk_pirates", -0.6f);
        sevencorp.setRelationship("junk_pirates_hounds", -0.6f);
        sevencorp.setRelationship("junk_pirates_junkboys", -0.6f);
        sevencorp.setRelationship("junk_pirates_technicians", -0.6f);
        sevencorp.setRelationship("the_cartel", -0.6f);
        sevencorp.setRelationship("nullorder", -0.6f);
        sevencorp.setRelationship("templars", -0.6f);
        sevencorp.setRelationship("crystanite_pir", -0.6f);
        sevencorp.setRelationship("infected", -0.6f);
        sevencorp.setRelationship("new_galactic_order", -1.0f);
        sevencorp.setRelationship("TF7070_D3C4", -0.6f);
        sevencorp.setRelationship("minor_pirate_1", -0.6f);
        sevencorp.setRelationship("minor_pirate_2", -0.6f);
        sevencorp.setRelationship("minor_pirate_3", -0.6f);
        sevencorp.setRelationship("minor_pirate_4", -0.6f);
        sevencorp.setRelationship("minor_pirate_5", -0.6f);
        sevencorp.setRelationship("minor_pirate_6", -0.6f);
            //other?? undecided
        sevencorp.setRelationship("yrxp", 0f);
        sevencorp.setRelationship("sylphon", -0.2f);
        sevencorp.setRelationship("Coalition", -0.3f);
    }
}