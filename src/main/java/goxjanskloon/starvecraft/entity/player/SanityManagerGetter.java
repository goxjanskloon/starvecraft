package goxjanskloon.starvecraft.entity.player;
public interface SanityManagerGetter{
    default SanityManager starvecraft$getSanityManager(){
        return null;
    }
}
