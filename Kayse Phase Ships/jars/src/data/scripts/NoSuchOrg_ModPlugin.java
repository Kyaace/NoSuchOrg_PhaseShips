/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import data.scripts.world.nosuchorg.NoSuchOrg_Gen;
import exerelin.campaign.SectorManager;

public class NoSuchOrg_ModPlugin extends BaseModPlugin {

    public static boolean isExerelin = false;
    public static boolean templarsExists = false;

    private static void initNSO() {
        boolean haveNexerelin = Global.getSettings().getModManager().isModEnabled("nexerelin");
        if (!haveNexerelin || SectorManager.getCorvusMode()){
            new NoSuchOrg_Gen().generate(Global.getSector());
            // Exerelin not found so continue and run normal generation code
        }
    }

    @Override
    public void onApplicationLoad() {
    }

    @Override
    public void onNewGame() {
        initNSO();
    }
}
