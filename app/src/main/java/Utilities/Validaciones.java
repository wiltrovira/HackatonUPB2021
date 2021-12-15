package Utilities;

import android.content.Context;
import android.content.pm.PackageManager;

public class Validaciones {

    /**
     *
     * @param nombrePaquete Paquete a validar
     * @param context
     * @return
     */
    public static boolean estaInstaladaAplicacion(String nombrePaquete, Context context) {

        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(nombrePaquete, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
