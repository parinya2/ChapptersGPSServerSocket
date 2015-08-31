// ----------------------------------------------------------------------------
// Copyright 2007-2015, GeoTelematic Solutions, Inc.
// All rights reserved
// ----------------------------------------------------------------------------
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// ----------------------------------------------------------------------------
// Description:
//  Main Entry point for a Template example server
// ----------------------------------------------------------------------------
// Change History:
//  2006/06/30  Martin D. Flynn
//     -Initial release
//  2006/07/27  Martin D. Flynn
//     -Moved constant information to 'Constants.java'
//  2009/08/07  Martin D. Flynn
//     -Updated to use DCServerConfig
// ----------------------------------------------------------------------------
package mygpstracker;

import java.lang.*;
import java.util.*;
import java.io.*;


import org.opengts.db.*;
import org.opengts.db.tables.Device;
import org.opengts.util.Print;
import org.opengts.util.RTConfig;

/**
*** <code>Main</code> - The main entry point for this device communication server (DCS)
*** module.
**/

public class Main
{
  
    /* command-line argument keys */
  //public  static final String ARG_DEVCODE[]   = new String[] { "devcode", "dcs" , "serverid" };
    public  static final String ARG_PARSEFILE[] = new String[] { "parse"  , "parseFile" };
    public  static final String ARG_HELP[]      = new String[] { "help"   , "h"   };
  //public  static final String ARG_TCP_PORT[]  = new String[] { "tcp"    , "p"   , "port" };
  //public  static final String ARG_UDP_PORT[]  = new String[] { "udp"    , "p"   , "port" };
    public  static final String ARG_CMD_PORT[]  = new String[] { "command", "cmd" };
    public  static final String ARG_START[]     = new String[] { "start"  };
    public  static final String ARG_DEBUG[]     = new String[] { "debug"  };
    public  static final String ARG_FORMAT[]    = new String[] { "format" , "parseFormat" };
    public  static final String ARG_INSERT[]    = new String[] { "insert" };

    public static String getServerName()
    {
        return Constants.DEVICE_CODE;
    }

    public static String getServerContextName()
    {
        return RTConfig.getContextName(Main.getServerName());
    }
    
    private static DCServerConfig dcServerCfg = null;
    public static DCServerConfig getServerConfig(Device dev)
    {
        if (dcServerCfg == null) {
            dcServerCfg = DCServerFactory.getServerConfig(Main.getServerContextName());
            DCServerConfig.startRemoteLogging(dcServerCfg);
        }
        return dcServerCfg;
    }
    
    public static void main(String argv[])
    {

        /* configure server for MySQL data store */
        //DBConfig.cmdLineInit(argv,false);  // main

        /* init configuration constants */
        TrackClientPacketHandler.configInit();
        TrackServer.configInit();

        /* header */
        String SEP = "--------------------------------------------------------------------------";
        System.out.println(SEP);
        System.out.println(Constants.TITLE_NAME + " Server Version " + Constants.VERSION);
        System.out.println("DeviceCode           : " + Constants.DEVICE_CODE);
        System.out.println("UniqueID Prefix      : " + "Chappters UniqueID Prefix");
        System.out.println("ParseFormat          : " + TrackClientPacketHandler.DATA_FORMAT_OPTION);
        System.out.println("MinimumSpeed         : " + TrackClientPacketHandler.MINIMUM_SPEED_KPH);
        System.out.println("EstimateOdom         : " + TrackClientPacketHandler.ESTIMATE_ODOMETER);
        System.out.println("TCP Idle Timeout     : " + TrackServer.getTcpIdleTimeout()    + " ms");
        System.out.println("TCP Packet Timeout   : " + TrackServer.getTcpPacketTimeout()  + " ms");
        System.out.println("TCP Session Timeout  : " + TrackServer.getTcpSessionTimeout() + " ms");
        System.out.println("UDP Idle Timeout     : " + TrackServer.getUdpIdleTimeout()    + " ms");
        System.out.println("UDP Packet Timeout   : " + TrackServer.getUdpPacketTimeout()  + " ms");
        System.out.println("UDP Session Timeout  : " + TrackServer.getUdpSessionTimeout() + " ms");
        System.out.println(Constants.COPYRIGHT);
        System.out.println(SEP);

        /* start server */
        if (true) {
            
            /* start port listeners */
            try {
                int tcpPorts[]  = {8800};
                int udpPorts[]  = null;
                int commandPort = 0;
                TrackServer.startTrackServer(tcpPorts, udpPorts, commandPort);
            } catch (Throwable t) { // trap any server exception
                System.out.print("Error: " + t);
            }
            
            /* wait here forever while the server is running in a thread */
            while (true) { 
                try { Thread.sleep(60L * 60L * 1000L); } catch (Throwable t) {} 
            }
            // control never reaches here
            
        }

        /* display usage */
        // control doesn't reach here
        System.exit(1);

    }

}
