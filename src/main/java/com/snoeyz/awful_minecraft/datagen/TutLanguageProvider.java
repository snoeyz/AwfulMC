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
    }
}
