package data.scripts.world.nosuchorg.stars;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.OrbitAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.StarTypes;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Terrain;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.StarAge;
import com.fs.starfarer.api.impl.campaign.procgen.StarSystemGenerator;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import data.scripts.world.nosuchorg.addMarketplace;

public class nusquam {

    public void generate(SectorAPI sector) {
        StarSystemAPI system = sector.createStarSystem("Nusquam");
        system.getLocation().set(6660, 6660);
        system.setBackgroundTextureFilename("graphics/backgrounds/hyperspace1.jpg");

        PlanetAPI Nusquam_sun = system.initStar("Nusquam", StarTypes.WHITE_DWARF, 550f, 500f); // 0.9 solar masses
        Nusquam_sun.setLightColorOverrideIfStar(Color.pink);
//        
//        // PLANETS 
//        
//
        PlanetAPI tombstone_planet = system.addPlanet("nso_tombstone", Nusquam_sun, "Tombstone", "irradiated", 300, 180, 6000, 180); // 0.0025 AU
        tombstone_planet.setCustomDescriptionId("nso_tombstone_description");
        tombstone_planet.getSpec().setRotation(5f); // 5 degrees/second = 7.2 days/revolution
        tombstone_planet.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "sindria"));
        tombstone_planet.getSpec().setGlowColor(new Color(255, 0, 255, 255));
        tombstone_planet.applySpecChanges();
        
        //addMarketplace(String factionID, SectorEntityToken primaryEntity, ArrayList<SectorEntityToken> connectedEntities, String name, 
        //int size, ArrayList<String> marketConditions, ArrayList<String> Industries, ArrayList<String> submarkets, float tariff)
        MarketAPI tombstone_Market = addMarketplace.addMarketplace("no_such_org", tombstone_planet, 
                null, "Tombstone", 5, 
                new ArrayList<>(Arrays.asList(Conditions.RUINS_SCATTERED, Conditions.FARMLAND_POOR, Conditions.ORGANICS_TRACE, Conditions.ORE_MODERATE, Conditions.IRRADIATED, Conditions.STEALTH_MINEFIELDS, Conditions.SOLAR_ARRAY, Conditions.FREE_PORT, Conditions.MILD_CLIMATE)), 
                new ArrayList<>(Arrays.asList(Industries.MILITARYBASE, Industries.SPACEPORT, Industries.POPULATION, Industries.FARMING, Industries.BATTLESTATION_HIGH, Industries.GROUNDDEFENSES)), 
                new ArrayList<>(Arrays.asList(Submarkets.SUBMARKET_STORAGE, Submarkets.SUBMARKET_BLACK, Submarkets.GENERIC_MILITARY, Submarkets.SUBMARKET_OPEN)),
                0.3f);
        
        tombstone_Market.addIndustry(Industries.ORBITALWORKS,new ArrayList<String>(Arrays.asList(Items.CORRUPTED_NANOFORGE)));
//        tombstone_Market.getIndustry(Industries.HIGHCOMMAND).setAICoreId(Commodities.ALPHA_CORE);
//        tombstone_Market.getIndustry(Industries.STARFORTRESS_HIGH).setAICoreId(Commodities.ALPHA_CORE);

        PlanetAPI boneyard_gas_planet = system.addPlanet("nso_boneyard_gas", Nusquam_sun, "Ossum", "ice_giant", 170, 320, 8000, 240); // 0.0025 AU

        PlanetAPI boneyard_planet = system.addPlanet("nso_boneyard", boneyard_gas_planet, "Boneyard", "tundra", 300, 180, 1200, 24); // 0.0025 AU
        boneyard_planet.setCustomDescriptionId("nso_crypt_description");
        boneyard_planet.getSpec().setRotation(5f); // 5 degrees/second = 7.2 days/revolution
//        boneyard_planet.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "sindria"));
        boneyard_planet.getSpec().setGlowColor(new Color(255, 0, 255, 255));
        boneyard_planet.applySpecChanges();
        
        //addMarketplace(String factionID, SectorEntityToken primaryEntity, ArrayList<SectorEntityToken> connectedEntities, String name, 
        //int size, ArrayList<String> marketConditions, ArrayList<String> Industries, ArrayList<String> submarkets, float tariff)
        MarketAPI boneyard_Market = addMarketplace.addMarketplace("no_such_org", boneyard_planet, 
                null, "Boneyard", 4, 
                new ArrayList<>(Arrays.asList(Conditions.RUINS_SCATTERED, Conditions.ORE_ABUNDANT, Conditions.RARE_ORE_MODERATE, Conditions.COLD, Conditions.STEALTH_MINEFIELDS, Conditions.FREE_PORT)), 
                new ArrayList<>(Arrays.asList(Industries.PATROLHQ, Industries.SPACEPORT, Industries.POPULATION, Industries.MINING, Industries.REFINING, Industries.ORBITALSTATION_HIGH, Industries.GROUNDDEFENSES)), 
                new ArrayList<>(Arrays.asList(Submarkets.SUBMARKET_STORAGE, Submarkets.SUBMARKET_BLACK, Submarkets.GENERIC_MILITARY, Submarkets.SUBMARKET_OPEN)),
                0.3f);
        

//        // STABLE LOCATIONS AND RELAYS
//        
        SectorEntityToken relay = system.addCustomEntity("nso_relay", "Ossum Relay", "comm_relay",
                                                         "no_such_org");
                relay.setCircularOrbit(Nusquam_sun, 220, 3500, 215);
        
        SectorEntityToken stableloc2 = system.addCustomEntity(null,null, "stable_location",Factions.NEUTRAL); 
		stableloc2.setCircularOrbitPointingDown(Nusquam_sun, 75, 4000, 215f);
                
        SectorEntityToken stableloc3 = system.addCustomEntity(null,null, "stable_location",Factions.NEUTRAL); 
		stableloc3.setCircularOrbitPointingDown(Nusquam_sun, 310, 3800, 215f);

//        // DECORATIONS 
//        
//
        //system.addAsteroidBelt(Nusquam_sun, 120, 5000, 128, 440, 470);
        system.addAsteroidBelt(Nusquam_sun, 1500, 5000, 200, 300, 600, Terrain.ASTEROID_BELT, "The Ring");

        
//        // JUMP POINTS 
//
        JumpPointAPI jumpPoint = Global.getFactory().createJumpPoint("Boneyard_jp", "Boneyard Jump Point");
        OrbitAPI orbit = Global.getFactory().createCircularOrbit(boneyard_gas_planet, 90, 550, 25);
        jumpPoint.setOrbit(orbit);
        jumpPoint.setRelatedPlanet(boneyard_gas_planet);
        jumpPoint.setStandardWormholeToHyperspaceVisual();
        system.addEntity(jumpPoint);
        
        JumpPointAPI jumpPoint2 = Global.getFactory().createJumpPoint("tombstone_jp", "Tombstone Portal");
        OrbitAPI orbit2 = Global.getFactory().createCircularOrbit(tombstone_planet, 100, 800, 25);
        jumpPoint2.setOrbit(orbit2);
        jumpPoint2.setRelatedPlanet(tombstone_planet);
        jumpPoint2.setStandardWormholeToHyperspaceVisual();
        system.addEntity(jumpPoint2);
        
        // PROCGEN
        

        float radiusAfter = StarSystemGenerator.addOrbitingEntities(system, Nusquam_sun, StarAge.AVERAGE,
                                                                    4, 6, // min/max entities to add
                                                                    10000, // radius to start adding at
                                                                    2, // name offset - next planet will be <system name> <roman numeral of this parameter + 1>
                                                                    true); // whether to use custom or system-name based names

        system.autogenerateHyperspaceJumpPoints(true, true); //begone evil clouds
        HyperspaceTerrainPlugin plugin = (HyperspaceTerrainPlugin) Misc.getHyperspaceTerrain().getPlugin();
        NebulaEditor editor = new NebulaEditor(plugin);
        float minRadius = plugin.getTileSize() * 2f;

        float radius = system.getMaxRadiusInHyperspace();
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f);
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f, 0.25f);
    }
}
