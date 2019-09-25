package com.example.scanbattles;

import com.example.scanbattles.models.Monster;

import java.util.ArrayList;

public class AllMonsters {

    private String[][] monsterStrings = {
        {"1","Falco","Zendra","Magic","5","3","6","3","7","3","2","4","7"},
        {"2","Gargoyle","Zendra","Magic","4","3","7","2","10","1","1","3","5"},
        {"3","Octeye","Zendra","Magic","8","2","8","2","8","2","3","5","7"},
        {"4","Vortex","Zendra","Magic","10","3","10","3","10","3","4","6","8"},
        {"5","Magi","Zendra","Magic","2","1","3","2","4","3","1","4","9"},
        {"6","Sludge","Zendra","Magic","7","1","8","1","9","1","2","3","6"},
        {"7","Grub","Zendra","Magic","1","1","1","1","1","1","3","4","5"},
        {"8","Dragant","Zendra","Magic","5","2","5","2","5","2","4","5","6"},
        {"9","Mutoid","Zendra","Magic","3","3","3","3","3","3","1","6","7"},
        {"10","Wart","Zendra","Magic","4","2","5","2","6","2","2","5","8"},
        {"11","Glyph","Zendra","Magic","1","3","1","3","1","3","3","3","9"},
        {"12","Archtopus","Zendra","Magic","3","2","3","2","3","2","4","4","7"},
        {"13","Zombie","Zendra","Magic","2","1","2","1","2","1","1","5","5"},
        {"14","Siren","Zendra","Magic","3","1","4","2","5","3","2","6","6"},
        {"15","Ingestamite","Zendra","Tech","5","2","5","2","5","2","1","3","5"},
        {"16","Tech Wasp","Zendra","Tech","9","3","9","3","9","3","2","4","6"},
        {"17","GFI","Zendra","Tech","6","2","6","2","6","2","3","5","7"},
        {"18","Dreadlock","Zendra","Tech","4","3","4","3","4","3","4","6","8"},
        {"19","Steel Tooth","Zendra","Tech","4","1","4","1","4","1","1","4","9"},
        {"20","Chain-o-Matic","Zendra","Tech","7","3","7","3","7","3","2","3","6"},
        {"21","Vacarac","Zendra","Tech","6","2","6","2","6","2","1","3","7"},
        {"22","M.A.X.","Zendra","Tech","4","2","4","2","4","2","2","4","8"},
        {"23","Big Top","Zendra","Tech","3","2","3","2","3","2","3","5","9"},
        {"24","Grendel","Zendra","Tech","8","3","8","3","8","3","4","5","8"},
        {"25","Junkyard","Zendra","Tech","6","1","6","1","6","1","3","3","9"},
        {"26","Aprina","Zendra","Tech","4","1","4","2","4","3","2","4","7"},
        {"27","Gearhead","Zendra","Tech","7","1","7","1","7","1","3","5","5"},
        {"28","Cyball","Zendra","Tech","8","3","8","3","8","3","2","6","6"},
        {"29","Webhog","Zendra","Power","1","1","2","1","3","1","1","6","5"},
        {"30","Spyticus","Zendra","Power","5","2","6","2","7","2","2","5","5"},
        {"31","Bog","Zendra","Power","3","2","4","2","5","2","1","6","5"},
        {"32","Spike","Zendra","Power","6","1","7","1","8","1","4","4","6"},
        {"33","Minotaur","Zendra","Power","5","1","5","2","5","3","1","5","7"},
        {"34","Screech","Zendra","Power","6","3","6","3","6","3","2","6","8"},
        {"35","Grizzler","Zendra","Power","7","2","8","2","9","2","3","6","9"},
        {"36","Sherpa","Zendra","Power","2","3","3","3","4","3","4","3","6"},
        {"37","Thugly","Zendra","Power","4","1","4","1","4","1","1","4","5"},
        {"38","Frenzy","Zendra","Power","5","3","5","3","5","3","1","6","5"},
        {"39","Cataclysm","Zendra","Power","7","2","7","2","7","2","3","6","7"},
        {"40","Yeti","Zendra","Power","4","1","4","1","4","1","4","3","8"},
        {"41","Ogriff","Zendra","Power","6","3","6","3","6","3","1","3","9"},
        {"42","Megapede","Zendra","Power","6","1","7","2","8","3","2","4","7"},
        {"43","Zolar","Pataak","Magic","6","2","7","2","8","2","2","5","8"},
        {"44","Ornithopter","Pataak","Magic","5","1","5","1","5","1","4","3","6"},
        {"45","Mystic","Pataak","Magic","5","2","5","2","5","2","4","3","8"},
        {"46","Alcorn","Pataak","Magic","9","1","9","1","9","1","1","4","9"},
        {"47","Gnome","Pataak","Magic","8","1","9","1","10","1","3","5","9"},
        {"48","Brain Stem","Pataak","Magic","2","3","2","3","2","3","3","6","7"},
        {"49","Talon","Pataak","Magic","4","3","4","3","4","3","4","4","6"},
        {"50","Oaken","Pataak","Magic","8","1","8","1","8","1","4","3","8"},
        {"51","Waspito","Pataak","Magic","8","3","8","3","8","3","3","6","5"},
        {"52","Mantavore","Pataak","Magic","4","2","4","2","4","2","2","4","7"},
        {"53","Djinni","Pataak","Magic","9","3","9","3","9","3","1","4","9"},
        {"54","Pitch Black","Pataak","Magic","10","1","10","1","10","1","4","6","9"},
        {"55","Unigruff","Pataak","Magic","3","3","3","3","3","3","1","6","7"},
        {"56","Vermin","Pataak","Magic","2","2","2","2","2","2","2","5","5"},
        {"57","Trax","Pataak","Tech","6","2","6","2","6","2","4","3","7"},
        {"58","Stryder","Pataak","Tech","8","3","8","3","8","3","2","4","9"},
        {"59","Tech 209","Pataak","Tech","4","2","4","2","4","2","4","4","6"},
        {"60","Bore Gore","Pataak","Tech","10","1","10","1","10","1","4","3","8"},
        {"61","Tic Tank","Pataak","Tech","7","2","7","2","7","2","1","4","8"},
        {"62","Skulltron","Pataak","Tech","8","3","8","3","8","3","3","6","7"},
        {"63","Recoil","Pataak","Tech","3","1","3","1","3","1","1","3","5"},
        {"64","Dragadrone","Pataak","Tech","7","2","7","2","7","2","4","5","6"},
        {"65","Orbital","Pataak","Tech","1","1","1","1","1","1","1","5","5"},
        {"66","Rotosquid","Pataak","Tech","4","2","6","3","6","3","3","5","7"},
        {"67","Quadrascope","Pataak","Tech","1","1","2","1","5","2","4","4","6"},
        {"68","RIP 187","Pataak","Tech","6","2","7","2","9","3","4","3","8"},
        {"69","Mean Steam","Pataak","Tech","4","1","6","2","7","2","1","5","7"},
        {"70","Booster","Pataak","Tech","4","2","5","3","6","3","4","5","6"},
        {"71","Antar","Pataak","Power","4","1","5","2","6","2","4","3","8"},
        {"72","Araba","Pataak","Power","6","2","7","2","9","3","3","6","7"},
        {"73","Drool","Pataak","Power","5","2","5","3","6","3","4","3","8"},
        {"74","Garroch","Pataak","Power","6","1","7","2","9","2","3","6","7"},
        {"75","Trog","Pataak","Power","4","1","7","1","7","2","2","5","6"},
        {"76","Thorn","Pataak","Power","6","1","7","1","8","1","1","4","5"},
        {"77","Sickle","Pataak","Power","5","2","5","3","6","3","4","3","6"},
        {"78","Gargantuan","Pataak","Power","6","1","7","2","8","2","4","6","9"},
        {"79","Thrash","Pataak","Power","2","1","4","2","5","2","2","5","8"},
        {"80","Sunami","Pataak","Power","4","1","7","1","7","2","1","5","7"},
        {"81","Knuckles","Pataak","Power","4","1","5","2","6","3","4","5","6"},
        {"82","Sand Trap","Pataak","Power","4","2","4","2","5","2","3","3","5"},
        {"83","Tuskarus","Pataak","Power","4","1","5","1","7","2","2","5","5"},
        {"84","Shegun","Pataak","Power","4","1","5","2","5","2","1","6","6"},
        {"85","Toxiana","Ujalu","Magic","6","3","6","3","6","3","4","3","9"},
        {"86","Suds","Ujalu","Magic","1","1","2","1","3","2","1","3","9"},
        {"87","Worm Hole","Ujalu","Magic","3","1","4","2","4","2","4","3","8"},
        {"88","Tribasaurus","Ujalu","Magic","6","1","7","2","9","3","3","6","7"},
        {"89","Bloat","Ujalu","Magic","4","1","7","1","8","1","2","5","6"},
        {"90","Gallop","Ujalu","Magic","4","2","5","3","6","3","2","4","5"},
        {"91","Triton","Ujalu","Magic","4","1","6","2","6","2","2","4","7"},
        {"92","Hydra","Ujalu","Magic","6","1","7","2","8","2","3","6","9"},
        {"93","Kelp","Ujalu","Magic","2","1","4","1","5","2","2","6","8"},
        {"94","Spirit Orb","Ujalu","Magic","7","2","7","3","7","3","2","4","6"},
        {"95","Locus","Ujalu","Magic","4","1","5","2","6","3","4","4","6"},
        {"96","Sea Serpent","Ujalu","Magic","4","2","4","2","5","2","3","3","5"},
        {"97","Hagatha","Ujalu","Magic","4","1","5","1","7","2","2","5","5"},
        {"98","Shadow Fin","Ujalu","Magic","4","1","5","2","5","2","1","6","9"},
        {"99","CRM 99","Ujalu","Tech","4","1","4","1","5","2","2","4","6"},
        {"100","Bo Bo","Ujalu","Tech","6","2","7","3","7","3","3","4","7"},
        {"101","Bull Bot","Ujalu","Tech","4","2","6","2","6","3","2","3","6"},
        {"102","Merc","Ujalu","Tech","6","2","6","3","7","2","2","4","6"},
        {"103","Blastar","Ujalu","Tech","4","1","5","1","6","2","1","4","5"},
        {"104","Mantis","Ujalu","Tech","3","2","4","2","5","2","3","5","9"},
        {"105","Crustoid","Ujalu","Tech","6","1","7","2","7","3","2","4","8"},
        {"106","Cyber Saw","Ujalu","Tech","4","2","7","3","8","3","1","3","7"},
        {"107","Scout","Ujalu","Tech","4","2","5","3","6","3","4","3","6"},
        {"108","Mag Drag","Ujalu","Tech","7","2","7","3","8","3","3","6","6"},
        {"109","Omnitron","Ujalu","Tech","6","1","7","1","8","2","2","5","8"},
        {"110","Lady Bot","Ujalu","Tech","4","1","4","1","5","1","1","4","9"},
        {"111","Skeeter","Ujalu","Tech","4","2","5","3","7","3","4","3","8"},
        {"112","Chronos","Ujalu","Tech","4","2","5","2","6","3","3","6","7"},
        {"113","Lex","Ujalu","Power","2","1","4","1","6","2","2","4","6"},
        {"114","Lava Bug","Ujalu","Power","7","1","7","1","7","2","2","5","5"},
        {"115","Mumster","Ujalu","Power","4","1","5","1","5","2","2","4","6"},
        {"116","Fina","Ujalu","Power","4","2","4","3","5","3","2","4","6"},
        {"117","Razor Back","Ujalu","Power","5","2","6","2","6","3","2","5","8"},
        {"118","Valdimere","Ujalu","Power","4","3","6","3","6","3","6","6","7"},
        {"119","Bob","Ujalu","Power","5","1","5","1","6","2","4","5","6"},
        {"120","Gluteus","Ujalu","Power","2","1","4","1","5","2","3","4","5"},
        {"121","Leech","Ujalu","Power","3","1","4","2","6","2","2","3","6"},
        {"122","Lash","Ujalu","Power","6","1","7","2","9","2","1","4","7"},
        {"123","Hobgoblin","Ujalu","Power","4","1","5","1","8","1","4","6","8"},
        {"124","Zoy","Ujalu","Power","4","1","5","1","6","2","3","5","7"},
        {"125","Sandstone","Ujalu","Power","5","2","5","2","6","2","2","4","6"},
        {"126","Nautilus","Ujalu","Power","6","1","7","2","8","2","3","6","9"}
    };
    ArrayList<Monster> allMonsters;

    public AllMonsters(){
        allMonsters = new ArrayList<>();

        for (String[] monsterString : monsterStrings) {

            Monster monster = new Monster();
            monster.id = Integer.parseInt(monsterString[0]);
            monster.name = monsterString[1];
            monster.tribe = monsterString[2];
            monster.classification = monsterString[3];
            monster.defenseLvl1 = Integer.parseInt(monsterString[4]);
            monster.speedLvl1 = Integer.parseInt(monsterString[5]);
            monster.defenseLvl2 = Integer.parseInt(monsterString[6]);
            monster.speedLvl2 = Integer.parseInt(monsterString[7]);
            monster.defenseLvl3 = Integer.parseInt(monsterString[8]);
            monster.speedLvl3 = Integer.parseInt(monsterString[9]);
            monster.attackLvl1 = Integer.parseInt(monsterString[10]);
            monster.attackLvl2 = Integer.parseInt(monsterString[11]);
            monster.attackLvl3 = Integer.parseInt(monsterString[12]);

            allMonsters.add(monster);

        }

    }

    public Monster getMonsterWithHash(int hash){
        int index = Math.abs(hash)%(monsterStrings.length*2);
        if(index >= allMonsters.size()){
            return null;
        }
        else return allMonsters.get(index);

    }

}
