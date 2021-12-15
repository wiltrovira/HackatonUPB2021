package application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class HackatonUPB2021Application extends Application {
    private static Context appContext;
    private static final String BASE_URL = "https://www.datos.gov.co/resource/2emd-i46m.json";

    private static final String DB_NAME = "hackatonupb2021.db";


    // Obtiene el número de cores diponibles: No siempre es el número máximo de cores del dispositivo
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    // Instantiates the queue of Runnables as a LinkedBlockingQueue
    private static final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
    // Tiempo máximo del thread en estado IDLE antes de terminar
    private static final int KEEP_ALIVE_TIME = 1;
    // Unidades de tiempo en segundos
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    // Creates a thread pool manager
    public static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            NUMBER_OF_CORES,       // Initial pool size
            NUMBER_OF_CORES,       // Max pool size
            KEEP_ALIVE_TIME,
            KEEP_ALIVE_TIME_UNIT,
            workQueue
    );


    @Override
    public void onCreate() {
        super.onCreate();
        HackatonUPB2021Application.appContext = getApplicationContext();
        /* If you has other classes that need context object to initialize when application is created,
         you can use the appContext here to process. */
    }

    /**
     * @return
     */
    public static Context getAppContext() {
        return HackatonUPB2021Application.appContext;
    }

    /**
     * getter
     */
    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getDbName() {
        return DB_NAME;
    }

    public static SharedPreferences getPreferenciasUsuario() {
        //Datos de preferencia del usuario
        SharedPreferences preferenciasUsuario = HackatonUPB2021Application.appContext.getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        return preferenciasUsuario;
    }
}


