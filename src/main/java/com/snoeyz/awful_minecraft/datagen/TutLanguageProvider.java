package com.snoeyz.awful_minecraft.datagen;

        import com.snoeyz.awful_minecraft.AwfulMinecraft;
        import com.snoeyz.awful_minecraft.setup.Registration;
        import net.minecraft.data.DataGenerator;
        import net.minecraftforge.common.data.LanguageProvider;

        import static com.snoeyz.awful_minecraft.setup.ModSetup.TAB_NAME;

public class TutLanguageProvider extends LanguageProvider {

    public TutLanguageProvider(DataGenerator gen, String locale) {
        super(gen, AwfulMinecraft.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + TAB_NAME, "Awful Minecraft");

        add(Registration.AWFUL_LOG.get(), "Awful Log");

        add("exhaustion.fumble.auditory.title", "A Zombie Growls");
        add("exhaustion.fumble.auditory.subtitle", "Or is your exhaustion getting to you?");
        add("exhaustion.fumble.blinking.title", "Blinking...");
        add("exhaustion.fumble.blinking.subtitle", "Your eyes seem to lose focus for a second...");
        add("exhaustion.fumble.clumsy.title", "Clumsy You");
        add("exhaustion.fumble.clumsy.subtitle", "Butterfingers...");
        add("exhaustion.fumble.dizzy.title", "Dizzy");
        add("exhaustion.fumble.dizzy.subtitle", "You suddenly ache for the respite of a bed");
        add("exhaustion.fumble.epiphora.title", "Eyes Watery");
        add("exhaustion.fumble.epiphora.subtitle", "You blink away tired tears");
        add("exhaustion.fumble.exhausted.title", "Exhausted");
        add("exhaustion.fumble.exhausted.subtitle", "You take a second to catch your breath");
        add("exhaustion.fumble.sleepy.title", "Geez");
        add("exhaustion.fumble.sleepy.subtitle", "You can't remember the last time you laid down..." );
        add("exhaustion.fumble.sluggish.title", "Feet Heavy");
        add("exhaustion.fumble.sluggish.subtitle", "You trudge on, despite your exhaustion");
        add("exhaustion.fumble.yawning.title", "You Yawn");
        add("exhaustion.fumble.yawning.subtitle", "As you fumble with your belongings...");
    }
}
