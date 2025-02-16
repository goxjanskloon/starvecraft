package goxjanskloon.starvecraft.entity.player;
public interface PlayerEntity{
    default SanityManager starvecraft$getSanityManager(){
        return null;
    }
}
