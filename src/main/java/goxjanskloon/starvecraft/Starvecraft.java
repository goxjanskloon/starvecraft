package goxjanskloon.starvecraft;
import goxjanskloon.starvecraft.item.Items;
import net.fabricmc.api.ModInitializer;
public class Starvecraft implements ModInitializer{
	public static final String MOD_ID="starvecraft";
	@Override public void onInitialize(){
		Items.initialize();
	}
}