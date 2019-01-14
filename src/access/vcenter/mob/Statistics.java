package access.vcenter.mob;

import com.vmware.vim25.mo.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Statistics {
    public static void main(String[] args) {

        Statistics.printAllVms();
    }

    public static void printAllVms() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            String VC_IP = "192.168.253.218"; // 192.168.56.128 dev-wcw P@ssw0rd
            String VC_NAME = "administrator@vsphere.local";
            String VC_PWD = "P@ssw0rd";
            String VC_URL = "https://" + VC_IP + "/sdk";

            ServiceInstance vcInstance = new ServiceInstance(new URL(VC_URL), VC_NAME, VC_PWD, true);


            ManagedEntity[] dcObjs = new InventoryNavigator(vcInstance.getRootFolder()).searchManagedEntities("Datacenter");
            System.out.println(dcObjs.length);
            Datacenter vcDatacenter = (Datacenter) dcObjs[0];
            ManagedEntity[] clusterObjs = new InventoryNavigator(vcDatacenter).searchManagedEntities("VirtualMachine");

            for (int j = 0; j < clusterObjs.length; j++) {
                VirtualMachine ds = (VirtualMachine) clusterObjs[j];
                System.out.println((j + 1) + "台/共" + clusterObjs.length + "台");
                System.out.println(ds.getName().equals("snow-testvm22"));
                if (null == ds.getConfig()) {
                    System.out.println(("没有changeVersion"));
                } else {
                    System.out.println((ds.getConfig().changeVersion));
                }
                String time = "";
                time = (null == ds.getConfig() ? "无modified time" : dateFormat.format(ds.getConfig().modified.getTime())); //
                System.out.println(time);
                String instanceUuid = "";
                instanceUuid = (null == ds.getConfig() ? "无instanceUuid" : ds.getConfig().instanceUuid);
                System.out.println(instanceUuid + " \n");
                System.out.println();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
