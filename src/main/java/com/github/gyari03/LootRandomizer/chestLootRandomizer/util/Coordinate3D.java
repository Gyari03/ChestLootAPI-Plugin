package com.github.gyari03.LootRandomizer.chestLootRandomizer.util;

public class Coordinate3D {
    private final int x;
    private final int y;
    private final int z;

    public Coordinate3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinate3D(String coords){
        String[] coordsArray = coords.split(",");

        if(coordsArray.length == 3){
            this.x = Integer.parseInt(coordsArray[0].replaceAll("[^0-9]", ""));
            this.y = Integer.parseInt(coordsArray[1].replaceAll("[^0-9]", ""));
            this.z = Integer.parseInt(coordsArray[2].replaceAll("[^0-9]", ""));
        }
        else{
            this.x = this.y = this.z = 0;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
}
