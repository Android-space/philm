package app.philm.in.modules.library;

import com.squareup.otto.Bus;

import android.content.Context;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import app.philm.in.AndroidStringFetcher;
import app.philm.in.lib.qualifiers.ApplicationContext;
import app.philm.in.lib.qualifiers.ForDatabase;
import app.philm.in.lib.qualifiers.GeneralPurpose;
import app.philm.in.lib.util.AndroidCountryProvider;
import app.philm.in.lib.util.AndroidLogger;
import app.philm.in.lib.util.BackgroundExecutor;
import app.philm.in.lib.util.CountryProvider;
import app.philm.in.lib.util.ImageHelper;
import app.philm.in.lib.util.Logger;
import app.philm.in.lib.util.PhilmBackgroundExecutor;
import app.philm.in.lib.util.StringFetcher;
import dagger.Module;
import dagger.Provides;

@Module(
        includes = ContextProvider.class,
        library = true
)
public class UtilProvider {

    @Provides @Singleton
    public Bus provideEventBus() {
        return new Bus();
    }

    @Provides @Singleton
    public Logger provideLogger() {
        return new AndroidLogger();
    }

    @Provides @Singleton
    public ImageHelper provideImageHelper() {
        return new ImageHelper();
    }

    @Provides @Singleton
    public CountryProvider provideCountryProvider(@ApplicationContext Context context) {
        return new AndroidCountryProvider(context);
    }

    @Provides @Singleton @GeneralPurpose
    public BackgroundExecutor provideMultiThreadExecutor() {
        final int numberCores = Runtime.getRuntime().availableProcessors();
        return new PhilmBackgroundExecutor(Executors.newFixedThreadPool(numberCores * 2 + 1));
    }

    @Provides @Singleton @ForDatabase
    public BackgroundExecutor provideDatabaseThreadExecutor() {
        return new PhilmBackgroundExecutor(Executors.newSingleThreadExecutor());
    }

    @Provides @Singleton
    public StringFetcher provideStringFetcher(@ApplicationContext Context context) {
        return new AndroidStringFetcher(context);
    }

}
