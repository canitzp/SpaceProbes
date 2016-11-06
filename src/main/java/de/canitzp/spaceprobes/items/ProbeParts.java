package de.canitzp.spaceprobes.items;

/**
 * @author canitzp
 *
 * future: Generator, Batterie, Extended Antenna, Sensors, Parachutes, Engines
 */
public class ProbeParts{

    public static final String HULL = "Hull";
    public static final String ANTENNA = "Antenna";

    public enum Hull{
        SIMPLE,
        BASIC,
        ADVANCED;

        private String name;
        Hull(String name){
            this.name = name;
        }
        Hull(){}
        @Override
        public String toString(){
            return this.name != null ? this.name : this.name().toLowerCase();
        }
    }

    public enum Antenna{
        TREE_SIMPLE,
        TREE_BASIC,
        TREE_ADVANCED,
        FOUR_SIMPLE,
        FOUR_BASIC,
        FOUR_ADVANCED;

        private String name;
        Antenna(String name){
            this.name = name;
        }
        Antenna(){}
        @Override
        public String toString(){
            return this.name != null ? this.name : this.name().toLowerCase();
        }
    }

}
