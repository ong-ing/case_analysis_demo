package com.onging.dynamic.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Properties;

@Component
@Slf4j
public class SSHConfiguration implements ServletContextInitializer {
    public static int PROXY_PORT =0;
    /**
     * 初始化
     */
    public SSHConfiguration() {
        try {
            log.info("开始启动SSH代理");
            Properties p = new Properties();
            // 读取resource文件夹下的ssh.properties文件
            p.load(getClass().getResourceAsStream("/ssh.properties"));
            // 如果配置文件包含ssh.forward.enabled属性，则使用ssh转发
            if (p.getProperty("ssh.remote.enabled") != null) {
                Session session = new JSch().getSession(p.getProperty("ssh.remote.username" ), p.getProperty("ssh.remote.host" ), Integer.valueOf(p.getProperty("ssh.remote.port" )));
                session.setConfig("StrictHostKeyChecking" , "no" );
                session.setPassword(p.getProperty("ssh.remote.password" ));
                session.connect();
                // 将机器分配的端口的请求转发到目标地址的数据库端口
                PROXY_PORT = session.setPortForwardingL(0,
                        p.getProperty("ssh.remote.target_host" ),
                        Integer.valueOf(p.getProperty("ssh.remote.target_port" )));
            } else {
                log.info("ssh forward is closed." );
            }
        } catch (IOException e) {
            log.error("SSH代理错误：ssh IOException failed." , e);
        } catch (JSchException e) {
            log.error("SSH代理错误：ssh JSchException failed." , e);
        } catch (Exception e) {
            log.error("SSH代理错误：ssh settings is failed. skip!" , e);
        }
        log.info("SSH代理开启成功！");
    }

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {

    }
    /**
     * 断开SSH连接
     */
    public void destroy() {
//		this.session.disconnect();
    }
}