package cn.wuhailong.webnote_scoreService;


import cn.wuhailong.webnote_scoreService.service.impl.ScoreServiceImpl;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RMI实现 积分服务
 * @author Administrator
 */
@SpringBootApplication
public class WebnoteScoreServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WebnoteScoreServiceApplication.class, args);
        try {
            LocateRegistry.createRegistry(8084);
            
            /*这里不能使用bean注入（Naming.rebind方法不支持）
            * 因为是new的，所以ScoreServiceImpl不属于容器管理，类中不能自动注入bean.
            * 而ScoreServiceImpl想要使用的ScoreDao的JPA代理类（JPA的Dao的bean），本该通过类型自动注入。
            * 为了实现，通过容器手动获取一个代理类ScoreDaoProxy的bean，其中通过类型自动注入JPA的Dao的bean。
            * 注意：ScoreServiceImpl不能交由容器管理，否则就不能再构造方法中初始化代理类bean（容器未初始化结束），需移到调用方法中。
            */
            Naming.rebind("rmi://localhost:8084/scoreService", new ScoreServiceImpl());
        } catch (Exception ex) {
            Logger.getLogger(WebnoteScoreServiceApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
