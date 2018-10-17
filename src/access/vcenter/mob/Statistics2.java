package access.vcenter.mob;

import java.net.URL;

import com.vmware.vim25.mo.Datacenter;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

public class Statistics2 {

	public static void main(String[] args) {

		try{


				String VC_IP="192.168.56.128";
				String VC_NAME="dev-wcw";
				String VC_PWD="P@ssw0rd";
				String VC_URL="https://"+ VC_IP + "/sdk";

				ServiceInstance vcInstance = new ServiceInstance(new URL(VC_URL), VC_NAME, VC_PWD,true);
				ManagedEntity[] dcObjs = new InventoryNavigator(vcInstance.getRootFolder()).searchManagedEntities("Datacenter");


				/*Datacenter vcDatacenter = (Datacenter)dcObjs[0];
				Datastore[] dsObjs = vcDatacenter.getDatastores();

				for (int j=0; j<dsObjs.length; j++) {

					Datastore ds = dsObjs[j];
					System.out.println(ds.getName());

					DatastoreSummary summary = ds.getSummary();
					System.out.println(summary.accessible);
					System.out.println(((summary.accessible) ? "Up" : "Down"));

				}*/


				Datacenter vcDatacenter = (Datacenter)dcObjs[0];
				ManagedEntity[] clusterObjs = new InventoryNavigator(vcDatacenter).searchManagedEntities("VirtualMachine");

				for (int j=0; j<clusterObjs.length; j++) {
					VirtualMachine ds = (VirtualMachine) clusterObjs[j];
					System.out.println( (j + 1) + "台");
					System.out.println(ds.getName());
					System.out.println(ds.getConfig().instanceUuid + " \n");

				}





//				for (int i=0; i<dcObjs.length; i++) {
//					List<List<String>> clustersNameList = new ArrayList<List<String>>();
//					Datacenter vcDatacenter = (Datacenter)dcObjs[i];
//					String dcName = vcDatacenter.getName();
//					System.out.println("   第("+(i+1)+")个数据中心 name="+dcName);
//
//
//					List<String> zoneLevel_DatastoreNames = new ArrayList<String>();
//					ManagedEntity[] clusterObjs = new InventoryNavigator(dcObjs[i]).searchManagedEntities("ClusterComputeResource");
//					if(clusterObjs.length >1) {//集群数量大于1 才需要考虑Zone级存储
//						for (ManagedEntity clusterObj : clusterObjs) {
//
//							List<String> datastoreNamesList = new ArrayList<String>();
//							ClusterComputeResource vcClusterTemp=(ClusterComputeResource)clusterObj;
//							Datastore[] dsObjsTemp = vcClusterTemp.getDatastores();
//							for (Datastore vcDs : dsObjsTemp) {
//								String datastoreName = vcDs.getName();
//
//								if(!datastoreNamesList.contains(datastoreName)) {
//									datastoreNamesList.add(datastoreName);
//								}
//							}
//							if(datastoreNamesList.size()>0) {
//								clustersNameList.add(datastoreNamesList);
//							}
//						}
//
//						if(clustersNameList.size() > 0) {
//							zoneLevel_DatastoreNames = clustersNameList.get(0);
//						}
//						if(clustersNameList.size() > 1) {//数据中心级存储,需要在每个集群下都存在,所以和每个集群取交集
//							for(int ii=1;ii<clustersNameList.size();ii++) {
//								zoneLevel_DatastoreNames.retainAll(clustersNameList.get(ii));//每个集群取交集
//							}
//						}
//
//						List<Datastore> zoneLevel_Datastores = new ArrayList<Datastore>();
//						if(zoneLevel_DatastoreNames.size()>0) {
//							Datastore[] dsObjs = vcDatacenter.getDatastores();
//
//							for (Datastore vcDs : dsObjs) {
//
//								String vcDsName = vcDs.getName();
//								if(zoneLevel_DatastoreNames.contains(vcDsName) && !zoneLevel_Datastores.contains(vcDs)) {
//									zoneLevel_Datastores.add(vcDs);
//								}
//							}
//						}
//
//						System.out.println("\n    数据中心级存储 数量: "+zoneLevel_Datastores.size());
//						for(int ii=0; ii<zoneLevel_Datastores.size(); ii++) {
//							String datastoreName = zoneLevel_Datastores.get(ii).getName();
//							long vcDsCapacity = zoneLevel_Datastores.get(ii).getSummary().getCapacity();
//							long vcDsfreeSpace = zoneLevel_Datastores.get(ii).getSummary().getFreeSpace();
//							long vcDsUsed = vcDsCapacity - vcDsfreeSpace;
//
////							System.out.println("         第 "+(ii+1)+" 块存储  name="+datastoreName+
////									" 总容量="+vcDsCapacity+
////									" 已使用="+vcDsUsed+
////									" 剩余="+vcDsfreeSpace);
//
//							System.out.println("     "+dcName+",Zone,,"+datastoreName+","+vcDsCapacity+","+vcDsUsed+","+vcDsfreeSpace);
//						}
//					}
//
//					for (int clusterIndex=0; clusterIndex<clusterObjs.length; clusterIndex++) {
//						List<Datastore> clusterLevel_Datastores = new ArrayList<Datastore>();
//						Map<String,String> cluster_hiddenHost = new HashMap<String,String>();
//						List<Datastore> localLevel_Datastores = new ArrayList<Datastore>();
//						ClusterComputeResource vcClusterTemp=(ClusterComputeResource)clusterObjs[clusterIndex];
//						String clusterName = vcClusterTemp.getName();
////						System.out.println("\n    第("+(clusterIndex+1)+")个集群 name="+clusterName);
//
//						//集群下的Host 用于 翻译 Host IP 和 比较 存储下的Host是不是在集群下所有Host上存在
//						ManagedEntity[] clusterHosts = new InventoryNavigator(clusterObjs[clusterIndex]).searchManagedEntities("HostSystem");
//						Map<String, String> clusterHostMap = new HashMap<String, String>();
//						for (ManagedEntity host : clusterHosts) {
//							clusterHostMap.put(host.getMOR().getVal(), host.getName());
//						}
//
//						Datastore[] dsObjsTemp = vcClusterTemp.getDatastores();
//						for (Datastore vcDs : dsObjsTemp) {
//
//							boolean accessible = vcDs.getSummary().accessible;
//							if(!accessible) {//存储不能访问
//								continue;
//							}
//
//							String datastoreName = vcDs.getName();
//							if(zoneLevel_DatastoreNames.contains(datastoreName)) {//数据中心级存储
//								continue;
//							}
//
//							boolean isShared = vcDs.getSummary().getMultipleHostAccess();
//							if(isShared) {//集群级存储
//								clusterLevel_Datastores.add(vcDs);
//								List<String> cluster_hidden = new ArrayList<String>();
//
//								for(ManagedEntity clusterHost : clusterHosts) {
//									HostSystem clusHost = (HostSystem)clusterHost;
//									String clusHostIp = clusHost.getName();
//
//									boolean isExists = false;
//									DatastoreHostMount[] dsHosts = vcDs.getHost();
//									for(DatastoreHostMount dsHost : dsHosts) {
//										ManagedObjectReference hostMor = dsHost.getKey();
//										String hostIP = clusterHostMap.get(hostMor.getVal());
//
//										if(clusHostIp.equals(hostIP)) {
//											isExists = true;
//											break;
//										}
//									}
//
//									if(!isExists) {
//										cluster_hidden.add(clusHostIp);
//									}
//								}
//
//								String tempStr = "";
//								for(int strIndes=0; strIndes<cluster_hidden.size(); strIndes++) {
//									if(strIndes == 0) {
//										tempStr = cluster_hidden.get(strIndes);
//									}else {
//										tempStr = tempStr+","+cluster_hidden.get(strIndes);
//									}
//								}
//
//								cluster_hiddenHost.put(datastoreName,tempStr);
//							}else {//本地存储
//								localLevel_Datastores.add(vcDs);
//							}
//						}
//
//						System.out.println("      集群级存储 数量: "+clusterLevel_Datastores.size());
//						for(int j=0; j<clusterLevel_Datastores.size(); j++) {
//							Datastore vcDs = clusterLevel_Datastores.get(j);
//							String datastoreName = vcDs.getName();
//							long vcDsCapacity = vcDs.getSummary().getCapacity();
//							long vcDsfreeSpace = vcDs.getSummary().getFreeSpace();
//							long vcDsUsed = vcDsCapacity - vcDsfreeSpace;
////							System.out.println("         第 "+(j+1)+" 块存储  name="+datastoreName+
////									" 总容量="+vcDsCapacity+
////									" 已使用="+vcDsUsed+
////									" 剩余="+vcDsfreeSpace);
//							System.out.println("     "+dcName+",Cluster,"+datastoreName+","+clusterName+","+vcDsCapacity+","+vcDsUsed+","+vcDsfreeSpace+","+(!"".equals(cluster_hiddenHost.get(datastoreName)) ? "\""+cluster_hiddenHost.get(datastoreName)+"\"":""));
//						}
//
//						System.out.println("      主机级存储 数量: "+localLevel_Datastores.size());
//						for(int j=0; j<localLevel_Datastores.size(); j++) {
//							Datastore vcDs = localLevel_Datastores.get(j);
//							String datastoreName = vcDs.getName();
//							long vcDsCapacity = vcDs.getSummary().getCapacity();
//							long vcDsfreeSpace = vcDs.getSummary().getFreeSpace();
//							long vcDsUsed = vcDsCapacity - vcDsfreeSpace;
//
////							DatastoreHostMount hostMount = vcDs.getHost()[0];
////							ManagedObjectReference hostMor = hostMount.getKey();
////							String hostIP = clusterHostMap.get(hostMor.getVal());
//
////							System.out.println("         第 "+(j+1)+" 块存储  name="+datastoreName+
////									" 总容量="+vcDsCapacity+
////									" 已使用="+vcDsUsed+
////									" 剩余="+vcDsfreeSpace+
////									" 主机IP="+(hostIP != null ? hostIP : ""));
//							System.out.println("     "+dcName+",Host,"+datastoreName+","+clusterName+","+vcDsCapacity+","+vcDsUsed+","+vcDsfreeSpace);
//						}
//
//					}
//					System.out.println("###################只查询第一个");
//					break;
//				}
		}catch(Exception e){}

	}
}
