package access.vcenter.mob;

import java.net.URL;

import com.vmware.vim25.mo.Datacenter;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

public class Statistics2 {
	public static void printAllVms() {
		try{
				String VC_IP="192.168.56.128";
				String VC_NAME="dev-wcw";
				String VC_PWD="P@ssw0rd";
				String VC_URL="https://"+ VC_IP + "/sdk";

				ServiceInstance vcInstance = new ServiceInstance(new URL(VC_URL), VC_NAME, VC_PWD,true);
				ManagedEntity[] dcObjs = new InventoryNavigator(vcInstance.getRootFolder()).searchManagedEntities("Datacenter");
				Datacenter vcDatacenter = (Datacenter)dcObjs[0];
				ManagedEntity[] clusterObjs = new InventoryNavigator(vcDatacenter).searchManagedEntities("VirtualMachine");

				for (int j=0; j<clusterObjs.length; j++) {
					VirtualMachine ds = (VirtualMachine) clusterObjs[j];
					System.out.println( (j + 1) + "台");
					System.out.println(ds.getName());
					System.out.println(ds.getConfig().instanceUuid + " \n");

				}
		}catch(Exception e){}

	}
}
