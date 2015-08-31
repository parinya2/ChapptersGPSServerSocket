// ----------------------------------------------------------------------------
// Copyright 2006-2010, GeoTelematic Solutions, Inc.
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
// Change History:
//  2006/03/26  Martin D. Flynn
//     -Initial release
//  2006/03/31  Martin D. Flynn
//     -Added new status codes:
//      STATUS_INITIALIZED, STATUS_WAYMARK
//  2007/01/25  Martin D. Flynn
//     -Added new status codes:
//      STATUS_QUERY, STATUS_LOW_BATTERY, STATUS_OBC_FAULT, STATUS_OBC_RANGE,
//      STATUS_OBC_RPM_RANGE, STATUS_OBC_FUEL_RANGE, STATUS_OBC_OIL_RANGE,
//      STATUS_OBC_TEMP_RANGE, STATUS_MOTION_MOVING
//     -Changed "Code" descriptions to start their indexing at '1' (instead of '0') 
//      since this string value is used to display to the user on various reports.
//  2007/03/11  Martin D. Flynn
//     -'GetCodeDescription' defaults to hex value of status code (or status code
//      name) if code is not found in the table.
//  2007/12/04  Martin D. Flynn
//     -Added new status codes: STATUS_EXCESS_BRAKING
//  2008/05/12  Martin D. Flynn
//     -Updated to Java 5.
// ----------------------------------------------------------------------------
package org.opendmtp.codes;

import java.util.*;

import org.opengts.util.*;

public class StatusCodes
{

// ----------------------------------------------------------------------------
// Reserved status codes: [E0-00 through FF-FF]
// Groups:
//      0xF0..  - Generic
//      0xF1..  - Motion
//      0xF2..  - Geofence
//      0xF4..  - Digital input/output
//      0xF6..  - Sensor input
//      0xF7..  - Temperature input
//      0xF9..  - OBC/J1708
//      0xFD..  - Device status
// ----------------------------------------------------------------------------

// ----------------------------------------------------------------------------
// No status code: 0x0000

    public static final int STATUS_NONE                 = 0x0000;

// ----------------------------------------------------------------------------
// Generic codes: 0xF000 to 0xF0FF

    public static final int STATUS_INITIALIZED          = 0xF010;
    // Description:
    //      General Status/Location information (event generated by some
    //      initialization function performed by the device).
    // Notes:
    //      - This contents of the payload must at least contain the current
    //      timestamp (and latitude and longitude if available).

    public static final int STATUS_LOCATION             = 0xF020;
    // Description:
    //      General Status/Location information.  This status code indicates
    //      no more than just the location of the device at a particular time.
    // Notes:
    //      - This contents of the payload must at least contain the current
    //      timestamp, latitude, and longitude.

    public static final int STATUS_WAYMARK              = 0xF030;
    // Description:
    //      General Status/Location information (event generated by manual user
    //      intervention at the device. ie. By pressing a 'Waymark' button).
    // Notes:
    //      - This contents of the payload must at least contain the current
    //      timestamp, latitude, and longitude.

    public static final int STATUS_QUERY                = 0xF040;
    // Description:
    //      General Status/Location information (event generated by 'query'
    //      from the server).
    // Notes:
    //      - This contents of the payload must at least contain the current
    //      timestamp, latitude, and longitude.

// ----------------------------------------------------------------------------
// Motion codes: 0xF100 to 0xF1FF

    public static final int STATUS_MOTION_START         = 0xF111;
    // Description:
    //      Device start of motion
    // Notes:
    //      The definition of motion-start is provided by property PROP_MOTION_START

    public static final int STATUS_MOTION_IN_MOTION     = 0xF112;
    // Description:
    //      Device in-motion interval
    // Notes:
    //      The in-motion interval is provided by property PROP_MOTION_IN_MOTION

    public static final int STATUS_MOTION_STOP          = 0xF113;
    // Description:
    //      Device stopped motion
    // Notes:
    //      The definition of motion-stop is provided by property PROP_MOTION_STOP

    public static final int STATUS_MOTION_DORMANT       = 0xF114;
    // Description:
    //      Device dormant interval (ie. not moving)
    // Notes:
    //      The dormant interval is provided by property PROP_MOTION_DORMANT

    public static final int STATUS_MOTION_EXCESS_SPEED  = 0xF11A;
    // Description:
    //      Device exceeded preset speed limit
    // Notes:
    //      The excess-speed threshold is provided by property PROP_MOTION_EXCESS_SPEED

    public static final int STATUS_MOTION_MOVING        = 0xF11C;
    // Description:
    //      Device is moving
    // Notes:
    //      - This status code may be used to indicating that the device was moving
    //      at the time the event was generated. It is typically not associated
    //      with the status codes STATUS_MOTION_START, STATUS_MOTION_STOP, and  
    //      STATUS_MOTION_IN_MOTION, and may be used independently of these codes.
    //      - This status code is typically used for devices that need to periodically
    //      report that they are moving, apart from the standard start/stop/in-motion
    //      events.

    public static final int STATUS_ODOM_0               = 0xF130;
    public static final int STATUS_ODOM_1               = 0xF131;
    public static final int STATUS_ODOM_2               = 0xF132;
    public static final int STATUS_ODOM_3               = 0xF133;
    public static final int STATUS_ODOM_4               = 0xF134;
    public static final int STATUS_ODOM_5               = 0xF135;
    public static final int STATUS_ODOM_6               = 0xF136;
    public static final int STATUS_ODOM_7               = 0xF137;
    // Description:
    //      Odometer value
    // Notes:
    //      The odometer limit is provided by property PROP_ODOMETER_#_LIMIT

    public static final int STATUS_ODOM_LIMIT_0         = 0xF140;
    public static final int STATUS_ODOM_LIMIT_1         = 0xF141;
    public static final int STATUS_ODOM_LIMIT_2         = 0xF142;
    public static final int STATUS_ODOM_LIMIT_3         = 0xF143;
    public static final int STATUS_ODOM_LIMIT_4         = 0xF144;
    public static final int STATUS_ODOM_LIMIT_5         = 0xF145;
    public static final int STATUS_ODOM_LIMIT_6         = 0xF146;
    public static final int STATUS_ODOM_LIMIT_7         = 0xF147;
    // Description:
    //      Odometer has exceeded a set limit
    // Notes:
    //      The odometer limit is provided by property PROP_ODOMETER_#_LIMIT

// ----------------------------------------------------------------------------
// Geofence: 0xF200 to 0xF2FF

    public static final int STATUS_GEOFENCE_ARRIVE      = 0xF210;
    // Description:
    //      Device arrived at geofence
    // Notes:
    //      - Client may wish to include FIELD_GEOFENCE_ID in the event packet.

    public static final int STATUS_GEOFENCE_DEPART      = 0xF230;
    // Description:
    //      Device departed geofence
    // Notes:
    //      - Client may wish to include FIELD_GEOFENCE_ID in the event packet.

    public static final int STATUS_GEOFENCE_VIOLATION   = 0xF250;
    // Description:
    //      Geofence violation
    // Notes:
    //      - Client may wish to include FIELD_GEOFENCE_ID in the event packet.

    public static final int STATUS_GEOFENCE_ACTIVE      = 0xF270;
    // Description:
    //      Geofence now active
    // Notes:
    //      - Client may wish to include FIELD_GEOFENCE_ID in the event packet.

    public static final int STATUS_GEOFENCE_INACTIVE    = 0xF280;
    // Description:
    //      Geofence now inactive
    // Notes:
    //      - Client may wish to include FIELD_GEOFENCE_ID in the event packet.

// ----------------------------------------------------------------------------
// Digital input/output (state change): 0xF400 to 0xF4FF

    public static final int STATUS_INPUT_STATE          = 0xF400;
    // Description:
    //      Current input ON state (bitmask)
    // Notes:
    //      - Client should include FIELD_INPUT_STATE in the event packet,
    //      otherwise this status code would have no meaning.

    public static final int STATUS_INPUT_ON             = 0xF402;
    // Description:
    //      Input turned ON
    // Notes:
    //      - Client should include FIELD_INPUT_ID in the event packet,
    //      otherwise this status code would have no meaning.
    //      - This status code may be used to indicate that an arbitrary input
    //      'thing' turned ON, and the 'thing' can be identified by the 'Input ID'.
    //      This 'ID' can also represent the index of a digital input.

    public static final int STATUS_INPUT_OFF            = 0xF404;
    // Description:
    //      Input turned OFF
    // Notes:
    //      - Client should include FIELD_INPUT_ID in the event packet,
    //      otherwise this status code would have no meaning.
    //      - This status code may be used to indicate that an arbitrary input
    //      'thing' turned OFF, and the 'thing' can be identified by the 'Input ID'.
    //      This 'ID' can also represent the index of a digital input.

    public static final int STATUS_OUTPUT_STATE         = 0xF406;
    // Description:
    //      Current output ON state (bitmask)
    // Notes:
    //      - Client should include FIELD_OUTPUT_STATE in the event packet,
    //      otherwise this status code would have no meaning.

    public static final int STATUS_OUTPUT_ON            = 0xF408;
    // Description:
    //      Output turned ON
    // Notes:
    //      - Client should include FIELD_OUTPUT_ID in the event packet,
    //      otherwise this status code would have no meaning.
    //      - This status code may be used to indicate that an arbitrary output
    //      'thing' turned ON, and the 'thing' can be identified by the 'Output ID'.
    //      This 'ID' can also represent the index of a digital output.

    public static final int STATUS_OUTPUT_OFF           = 0xF40A;
    // Description:
    //      Output turned OFF
    // Notes:
    //      - Client should include FIELD_OUTPUT_ID in the event packet,
    //      otherwise this status code would have no meaning.
    //      - This status code may be used to indicate that an arbitrary output
    //      'thing' turned OFF, and the 'thing' can be identified by the 'Output ID'.
    //      This 'ID' can also represent the index of a digital output.

    public static final int STATUS_INPUT_ON_00          = 0xF420;
    public static final int STATUS_INPUT_ON_01          = 0xF421;
    public static final int STATUS_INPUT_ON_02          = 0xF422;
    public static final int STATUS_INPUT_ON_03          = 0xF423;
    public static final int STATUS_INPUT_ON_04          = 0xF424;
    public static final int STATUS_INPUT_ON_05          = 0xF425;
    public static final int STATUS_INPUT_ON_06          = 0xF426;
    public static final int STATUS_INPUT_ON_07          = 0xF427;
    // Description:
    //      Digital input state changed to ON

    public static final int STATUS_INPUT_OFF_00         = 0xF440;
    public static final int STATUS_INPUT_OFF_01         = 0xF441;
    public static final int STATUS_INPUT_OFF_02         = 0xF442;
    public static final int STATUS_INPUT_OFF_03         = 0xF443;
    public static final int STATUS_INPUT_OFF_04         = 0xF444;
    public static final int STATUS_INPUT_OFF_05         = 0xF445;
    public static final int STATUS_INPUT_OFF_06         = 0xF446;
    public static final int STATUS_INPUT_OFF_07         = 0xF447;
    // Description:
    //      Digital input state changed to OFF

    public static final int STATUS_OUTPUT_ON_00         = 0xF460;
    public static final int STATUS_OUTPUT_ON_01         = 0xF461;
    public static final int STATUS_OUTPUT_ON_02         = 0xF462;
    public static final int STATUS_OUTPUT_ON_03         = 0xF463;
    public static final int STATUS_OUTPUT_ON_04         = 0xF464;
    public static final int STATUS_OUTPUT_ON_05         = 0xF465;
    public static final int STATUS_OUTPUT_ON_06         = 0xF466;
    public static final int STATUS_OUTPUT_ON_07         = 0xF467;
    // Description:
    //      Digital output state set to ON

    public static final int STATUS_OUTPUT_OFF_00        = 0xF480;
    public static final int STATUS_OUTPUT_OFF_01        = 0xF481;
    public static final int STATUS_OUTPUT_OFF_02        = 0xF482;
    public static final int STATUS_OUTPUT_OFF_03        = 0xF483;
    public static final int STATUS_OUTPUT_OFF_04        = 0xF484;
    public static final int STATUS_OUTPUT_OFF_05        = 0xF485;
    public static final int STATUS_OUTPUT_OFF_06        = 0xF486;
    public static final int STATUS_OUTPUT_OFF_07        = 0xF487;
    // Description:
    //      Digital output state set to OFF

    public static final int STATUS_ELAPSED_00           = 0xF4A0;
    public static final int STATUS_ELAPSED_01           = 0xF4A1;
    public static final int STATUS_ELAPSED_02           = 0xF4A2;
    public static final int STATUS_ELAPSED_03           = 0xF4A3;
    public static final int STATUS_ELAPSED_04           = 0xF4A4;
    public static final int STATUS_ELAPSED_05           = 0xF4A5;
    public static final int STATUS_ELAPSED_06           = 0xF4A6;
    public static final int STATUS_ELAPSED_07           = 0xF4A7;
    // Description:
    //      Elapsed time
    // Notes:
    //      - Client should include FIELD_ELAPSED_TIME in the event packet,
    //      otherwise this status code would have no meaning.

    public static final int STATUS_ELAPSED_LIMIT_00     = 0xF4B0;
    public static final int STATUS_ELAPSED_LIMIT_01     = 0xF4B1;
    public static final int STATUS_ELAPSED_LIMIT_02     = 0xF4B2;
    public static final int STATUS_ELAPSED_LIMIT_03     = 0xF4B3;
    public static final int STATUS_ELAPSED_LIMIT_04     = 0xF4B4;
    public static final int STATUS_ELAPSED_LIMIT_05     = 0xF4B5;
    public static final int STATUS_ELAPSED_LIMIT_06     = 0xF4B6;
    public static final int STATUS_ELAPSED_LIMIT_07     = 0xF4B7;
    // Description:
    //      Elapsed timer has exceeded a set limit
    // Notes:
    //      - Client should include FIELD_ELAPSED_TIME in the event packet,
    //      otherwise this status code would have no meaning.

// ----------------------------------------------------------------------------
// Analog/etc sensor values (extra data): 0xF600 to 0xF6FF

    public static final int STATUS_SENSOR32_0           = 0xF600;
    public static final int STATUS_SENSOR32_1           = 0xF601;
    public static final int STATUS_SENSOR32_2           = 0xF602;
    public static final int STATUS_SENSOR32_3           = 0xF603;
    public static final int STATUS_SENSOR32_4           = 0xF604;
    public static final int STATUS_SENSOR32_5           = 0xF605;
    public static final int STATUS_SENSOR32_6           = 0xF606;
    public static final int STATUS_SENSOR32_7           = 0xF607;
    // Description:
    //      32-bit unsigned sensor value
    // Notes:
    //      - Client should include FIELD_SENSOR32 in the event packet,
    //      otherwise this status code would have no meaning.
    //      - The server must be able to convert this 32-bit value to something
    //      meaningful to the user.  This can be done using the following formula:
    //         Actual_Value = ((double)Sensor32_Value * <Gain>) + <Offset>;
    //      Where <Gain> & <Offset> are user configurable values provided at setup.
    //      For instance: Assume Sensor32-0 contains a temperature value that can
    //      have a range of -40.0C to +125.0C.  The client would encode -14.7C
    //      by adding 40.0 and multiplying by 10.0.  The resulting value would be
    //      253.  The server would then be configured to know how to convert this
    //      value back into the proper temperature using the above formula by
    //      substituting 0.1 for <Gain>, and -40.0 for <Offset>: eg.
    //          -14.7 = ((double)253 * 0.1) + (-40.0);

    public static final int STATUS_SENSOR32_RANGE_0     = 0xF620;
    public static final int STATUS_SENSOR32_RANGE_1     = 0xF621;
    public static final int STATUS_SENSOR32_RANGE_2     = 0xF622;
    public static final int STATUS_SENSOR32_RANGE_3     = 0xF623;
    public static final int STATUS_SENSOR32_RANGE_4     = 0xF624;
    public static final int STATUS_SENSOR32_RANGE_5     = 0xF625;
    public static final int STATUS_SENSOR32_RANGE_6     = 0xF626;
    public static final int STATUS_SENSOR32_RANGE_7     = 0xF627;
    // Description:
    //      32-bit unsigned sensor value out-of-range violation
    // Notes:
    //      - Client should include FIELD_SENSOR32 in the event packet,
    //      otherwise this status code would have no meaning.

// ----------------------------------------------------------------------------
// Temperature sensor values (extra data): 0xF700 to 0xF7FF

    public static final int STATUS_TEMPERATURE_0        = 0xF710;
    public static final int STATUS_TEMPERATURE_1        = 0xF711;
    public static final int STATUS_TEMPERATURE_2        = 0xF712;
    public static final int STATUS_TEMPERATURE_3        = 0xF713;
    public static final int STATUS_TEMPERATURE_4        = 0xF714;
    public static final int STATUS_TEMPERATURE_5        = 0xF715;
    public static final int STATUS_TEMPERATURE_6        = 0xF716;
    public static final int STATUS_TEMPERATURE_7        = 0xF717;
    // Description:
    //      Temperature value
    // Notes:
    //      - Client should include at least the field FIELD_TEMP_AVER in the 
    //      event packet, and may also wish to include FIELD_TEMP_LOW and
    //      FIELD_TEMP_HIGH.

    public static final int STATUS_TEMPERATURE_RANGE_0  = 0xF730;
    public static final int STATUS_TEMPERATURE_RANGE_1  = 0xF731;
    public static final int STATUS_TEMPERATURE_RANGE_2  = 0xF732;
    public static final int STATUS_TEMPERATURE_RANGE_3  = 0xF733;
    public static final int STATUS_TEMPERATURE_RANGE_4  = 0xF734;
    public static final int STATUS_TEMPERATURE_RANGE_5  = 0xF735;
    public static final int STATUS_TEMPERATURE_RANGE_6  = 0xF736;
    public static final int STATUS_TEMPERATURE_RANGE_7  = 0xF737;
    // Description:
    //      Temperature value out-of-range [low/high/average]
    // Notes:
    //      - Client should include at least one of the fields FIELD_TEMP_AVER,
    //      FIELD_TEMP_LOW, or FIELD_TEMP_HIGH.

    public static final int STATUS_TEMPERATURE          = 0xF7F1;
    // Description:
    //      All temperature averages [aver/aver/aver/...]

// ----------------------------------------------------------------------------
// Miscellaneous

    public static final int STATUS_LOGIN                = 0xF811;
    // Description:
    //      Generic 'login'

    public static final int STATUS_LOGOUT               = 0xF812;
    // Description:
    //      Generic 'logout'

    public static final int STATUS_CONNECT              = 0xF821;
    // Description:
    //      Connect/Hook/On

    public static final int STATUS_DISCONNECT           = 0xF822;
    // Description:
    //      Disconnect/Drop/Off

    public static final int STATUS_ACK                  = 0xF831;
    // Description:
    //      Acknowledge

    public static final int STATUS_NAK                  = 0xF832;
    // Description:
    //      Negative Acknowledge

// ----------------------------------------------------------------------------
// OBC/J1708 status: 0xF900 to 0xF9FF

    public static final int STATUS_OBC_FAULT            = 0xF911;
    // Description:
    //      OBC/J1708 fault code occurred.
    // Notes:
    //      - Client should include the field FIELD_OBC_J1708_FAULT

    public static final int STATUS_OBC_RANGE            = 0xF920;
    // Description:
    //      Generic OBC/J1708 value out-of-range
    // Notes:
    //      - Client should include at least one of the FIELD_OBC_xxxxx fields.

    public static final int STATUS_OBC_RPM_RANGE        = 0xF922;
    // Description:
    //      OBC/J1708 RPM out-of-range
    // Notes:
    //      - Client should include the field FIELD_OBC_ENGINE_RPM.

    public static final int STATUS_OBC_FUEL_RANGE       = 0xF924;
    // Description:
    //      OBC/J1708 Fuel level out-of-range (ie. to low)
    // Notes:
    //      - Client should include the field FIELD_OBC_FUEL_LEVEL.

    public static final int STATUS_OBC_OIL_RANGE        = 0xF926;
    // Description:
    //      OBC/J1708 Oil level out-of-range (ie. to low)

    public static final int STATUS_OBC_TEMP_RANGE       = 0xF928;
    // Description:
    //      OBC/J1708 Temperature out-of-range
    // Notes:
    //      - Client should include at least one of the FIELD_OBC_xxxxx fields
    //      which indicates an OBC temperature out-of-range.

    public static final int STATUS_EXCESS_BRAKING       = 0xF930;
    // Description:
    //      Excessive acceleration/deceleration detected

// ----------------------------------------------------------------------------
// Internal device status

    public static final int STATUS_LOW_BATTERY          = 0xFD10;
    // Description:
    //      Low battery indicator

// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------

    private static final String CODE_PREFIX = "DMT.";

    public static class Code
    {
        private int     code    = 0;
        private String  name    = "";
        private String  desc    = null;
        private boolean hiPri   = false;
        public Code(int code, String name, String desc) {
            this(code, name, desc, false);
        }
        public Code(int code, String name, String desc, boolean higherPri) {
            this.code   = code;
            this.name   = CODE_PREFIX + name;
            this.desc   = desc;
            this.hiPri  = higherPri;
        }
        public int getCode() {
            return this.code;
        }
        public String getName() {
            if (this.name == null) { this.name = ""; }
            if (this.code > 0) {
                return this.name + "[" + this.code + "]";
            } else {
                return this.name;
            }
        }
        public String getDescription() {
            if (this.desc != null) {
                return this.desc;
            } else {
                return this.getName();
            }
        }
        public boolean isHigherPriority() {
            return this.hiPri;
        }
    }
    
    private static Code _code[] = new Code[] {
        
        //       16-bit Code                 Name             Description     HiPri
        new Code(STATUS_INITIALIZED        , "INITIALIZED"  , "Initialized" ),
        new Code(STATUS_LOCATION           , "LOCATION"     , "Location"    , true),
        new Code(STATUS_WAYMARK            , "WAYMARK"      , "Waymark"     , true),
        new Code(STATUS_QUERY              , "QUERY"        , "Query"       , true),

        new Code(STATUS_MOTION_START       , "MOT.START"    , "Start"       , true),
        new Code(STATUS_MOTION_IN_MOTION   , "MOT.MOVING"   , "InMotion"    ),
        new Code(STATUS_MOTION_STOP        , "MOT.STOP"     , "Stop"        , true),
        new Code(STATUS_MOTION_DORMANT     , "MOT.DORMANT"  , "Dormant"     ),
        new Code(STATUS_MOTION_EXCESS_SPEED, "MOT.SPEED"    , "Speeding"    ),
        new Code(STATUS_MOTION_MOVING      , "MOT.MOVING"   , "Moving"      ),

        new Code(STATUS_ODOM_0             , "ODO.0"        , "Odometer_1"  ),
        new Code(STATUS_ODOM_1             , "ODO.1"        , "Odometer_2"  ),
        new Code(STATUS_ODOM_2             , "ODO.2"        , "Odometer_3"  ),
        new Code(STATUS_ODOM_3             , "ODO.3"        , "Odometer_4"  ),
        new Code(STATUS_ODOM_4             , "ODO.4"        , "Odometer_5"  ),
        new Code(STATUS_ODOM_5             , "ODO.5"        , "Odometer_6"  ),
        new Code(STATUS_ODOM_6             , "ODO.6"        , "Odometer_7"  ),
        new Code(STATUS_ODOM_7             , "ODO.7"        , "Odometer_8"  ),
        new Code(STATUS_ODOM_LIMIT_0       , "ODO.LIM.0"    , "OdoLimit_1"  , true),
        new Code(STATUS_ODOM_LIMIT_1       , "ODO.LIM.1"    , "OdoLimit_2"  , true),
        new Code(STATUS_ODOM_LIMIT_2       , "ODO.LIM.2"    , "OdoLimit_3"  , true),
        new Code(STATUS_ODOM_LIMIT_3       , "ODO.LIM.3"    , "OdoLimit_4"  , true),
        new Code(STATUS_ODOM_LIMIT_4       , "ODO.LIM.4"    , "OdoLimit_5"  , true),
        new Code(STATUS_ODOM_LIMIT_5       , "ODO.LIM.5"    , "OdoLimit_6"  , true),
        new Code(STATUS_ODOM_LIMIT_6       , "ODO.LIM.6"    , "OdoLimit_7"  , true),
        new Code(STATUS_ODOM_LIMIT_7       , "ODO.LIM.7"    , "OdoLimit_8"  , true),
        
        new Code(STATUS_GEOFENCE_ARRIVE    , "GEO.ARR"      , "Arrive"      , true),
        new Code(STATUS_GEOFENCE_DEPART    , "GEO.DEP"      , "Depart"      , true),
        new Code(STATUS_GEOFENCE_VIOLATION , "GEO.VIO"      , "Geofence"    , true),
        new Code(STATUS_GEOFENCE_ACTIVE    , "GEO.ACT"      , "GeofActive"  ),
        new Code(STATUS_GEOFENCE_INACTIVE  , "GEO.INA"      , "GeofInactive"),
        
        new Code(STATUS_INPUT_STATE        , "INP.STA"      , "Inputs"      ),
        new Code(STATUS_INPUT_ON           , "INP.ON"       , "InputOn"     ),
        new Code(STATUS_INPUT_OFF          , "INP.OFF"      , "InputOff"    ),
        
        new Code(STATUS_OUTPUT_STATE       , "OUT.ST"       , "Outputs"     ),
        new Code(STATUS_OUTPUT_ON          , "OUT.ON"       , "OutputOn"    ),
        new Code(STATUS_OUTPUT_OFF         , "OUT.OFF"      , "OutputOff"   ),
        
        new Code(STATUS_INPUT_ON_00        , "INP.ON.0"    , "InputOn_1"    ),
        new Code(STATUS_INPUT_ON_01        , "INP.ON.1"    , "InputOn_2"    ),
        new Code(STATUS_INPUT_ON_02        , "INP.ON.2"    , "InputOn_3"    ),
        new Code(STATUS_INPUT_ON_03        , "INP.ON.3"    , "InputOn_4"    ),
        new Code(STATUS_INPUT_ON_04        , "INP.ON.4"    , "InputOn_5"    ),
        new Code(STATUS_INPUT_ON_05        , "INP.ON.5"    , "InputOn_6"    ),
        new Code(STATUS_INPUT_ON_06        , "INP.ON.6"    , "InputOn_7"    ),
        new Code(STATUS_INPUT_ON_07        , "INP.ON.7"    , "InputOn_8"    ),
        new Code(STATUS_INPUT_OFF_00       , "INP.OFF.0"   , "InputOff_1"   ),
        new Code(STATUS_INPUT_OFF_01       , "INP.OFF.1"   , "InputOff_2"   ),
        new Code(STATUS_INPUT_OFF_02       , "INP.OFF.2"   , "InputOff_3"   ),
        new Code(STATUS_INPUT_OFF_03       , "INP.OFF.3"   , "InputOff_4"   ),
        new Code(STATUS_INPUT_OFF_04       , "INP.OFF.4"   , "InputOff_5"   ),
        new Code(STATUS_INPUT_OFF_05       , "INP.OFF.5"   , "InputOff_6"   ),
        new Code(STATUS_INPUT_OFF_06       , "INP.OFF.6"   , "InputOff_7"   ),
        new Code(STATUS_INPUT_OFF_07       , "INP.OFF.7"   , "InputOff_8"   ),
        
        new Code(STATUS_OUTPUT_ON_00       , "OUT.ON.0"    , "OutputOn_1"   ),
        new Code(STATUS_OUTPUT_ON_01       , "OUT.ON.1"    , "OutputOn_2"   ),
        new Code(STATUS_OUTPUT_ON_02       , "OUT.ON.2"    , "OutputOn_3"   ),
        new Code(STATUS_OUTPUT_ON_03       , "OUT.ON.3"    , "OutputOn_4"   ),
        new Code(STATUS_OUTPUT_ON_04       , "OUT.ON.4"    , "OutputOn_5"   ),
        new Code(STATUS_OUTPUT_ON_05       , "OUT.ON.5"    , "OutputOn_6"   ),
        new Code(STATUS_OUTPUT_ON_06       , "OUT.ON.6"    , "OutputOn_7"   ),
        new Code(STATUS_OUTPUT_ON_07       , "OUT.ON.7"    , "OutputOn_8"   ),
        new Code(STATUS_OUTPUT_OFF_00      , "OUT.OFF.0"   , "OutputOff_1"  ),
        new Code(STATUS_OUTPUT_OFF_01      , "OUT.OFF.1"   , "OutputOff_2"  ),
        new Code(STATUS_OUTPUT_OFF_02      , "OUT.OFF.2"   , "OutputOff_3"  ),
        new Code(STATUS_OUTPUT_OFF_03      , "OUT.OFF.3"   , "OutputOff_4"  ),
        new Code(STATUS_OUTPUT_OFF_04      , "OUT.OFF.4"   , "OutputOff_5"  ),
        new Code(STATUS_OUTPUT_OFF_05      , "OUT.OFF.5"   , "OutputOff_6"  ),
        new Code(STATUS_OUTPUT_OFF_06      , "OUT.OFF.6"   , "OutputOff_7"  ),
        new Code(STATUS_OUTPUT_OFF_07      , "OUT.OFF.7"   , "OutputOff_8"  ),
        
        new Code(STATUS_ELAPSED_00         , "ELA.0"        , "Elapse_1"    ),
        new Code(STATUS_ELAPSED_01         , "ELA.1"        , "Elapse_2"    ),
        new Code(STATUS_ELAPSED_02         , "ELA.2"        , "Elapse_3"    ),
        new Code(STATUS_ELAPSED_03         , "ELA.3"        , "Elapse_4"    ),
        new Code(STATUS_ELAPSED_04         , "ELA.4"        , "Elapse_5"    ),
        new Code(STATUS_ELAPSED_05         , "ELA.5"        , "Elapse_6"    ),
        new Code(STATUS_ELAPSED_06         , "ELA.6"        , "Elapse_7"    ),
        new Code(STATUS_ELAPSED_07         , "ELA.7"        , "Elapse_8"    ),
        new Code(STATUS_ELAPSED_LIMIT_00   , "ELA.LIM.0"    , "ElaLimit_1"  , true),
        new Code(STATUS_ELAPSED_LIMIT_01   , "ELA.LIM.1"    , "ElaLimit_2"  , true),
        new Code(STATUS_ELAPSED_LIMIT_02   , "ELA.LIM.2"    , "ElaLimit_3"  , true),
        new Code(STATUS_ELAPSED_LIMIT_03   , "ELA.LIM.3"    , "ElaLimit_4"  , true),
        new Code(STATUS_ELAPSED_LIMIT_04   , "ELA.LIM.4"    , "ElaLimit_5"  , true),
        new Code(STATUS_ELAPSED_LIMIT_05   , "ELA.LIM.5"    , "ElaLimit_6"  , true),
        new Code(STATUS_ELAPSED_LIMIT_06   , "ELA.LIM.6"    , "ElaLimit_7"  , true),
        new Code(STATUS_ELAPSED_LIMIT_07   , "ELA.LIM.7"    , "ElaLimit_8"  , true),
        
        new Code(STATUS_SENSOR32_0         , "SEN32.0"      , "Sensor32_1"  ),
        new Code(STATUS_SENSOR32_1         , "SEN32.1"      , "Sensor32_2"  ),
        new Code(STATUS_SENSOR32_2         , "SEN32.2"      , "Sensor32_3"  ),
        new Code(STATUS_SENSOR32_3         , "SEN32.3"      , "Sensor32_4"  ),
        new Code(STATUS_SENSOR32_3         , "SEN32.4"      , "Sensor32_5"  ),
        new Code(STATUS_SENSOR32_4         , "SEN32.5"      , "Sensor32_6"  ),
        new Code(STATUS_SENSOR32_5         , "SEN32.6"      , "Sensor32_7"  ),
        new Code(STATUS_SENSOR32_7         , "SEN32.7"      , "Sensor32_8"  ),
        new Code(STATUS_SENSOR32_RANGE_0   , "SEN32.LIM.0"  , "Sen32Range_1", true),
        new Code(STATUS_SENSOR32_RANGE_1   , "SEN32.LIM.1"  , "Sen32Range_2", true),
        new Code(STATUS_SENSOR32_RANGE_2   , "SEN32.LIM.2"  , "Sen32Range_3", true),
        new Code(STATUS_SENSOR32_RANGE_3   , "SEN32.LIM.3"  , "Sen32Range_4", true),
        new Code(STATUS_SENSOR32_RANGE_4   , "SEN32.LIM.4"  , "Sen32Range_5", true),
        new Code(STATUS_SENSOR32_RANGE_5   , "SEN32.LIM.5"  , "Sen32Range_6", true),
        new Code(STATUS_SENSOR32_RANGE_6   , "SEN32.LIM.6"  , "Sen32Range_7", true),
        new Code(STATUS_SENSOR32_RANGE_7   , "SEN32.LIM.7"  , "Sen32Range_8", true),
        
        new Code(STATUS_TEMPERATURE_0      , "TMP.0"        , "Temp_1"      ),
        new Code(STATUS_TEMPERATURE_1      , "TMP.1"        , "Temp_2"      ),
        new Code(STATUS_TEMPERATURE_2      , "TMP.2"        , "Temp_3"      ),
        new Code(STATUS_TEMPERATURE_3      , "TMP.3"        , "Temp_4"      ),
        new Code(STATUS_TEMPERATURE_4      , "TMP.4"        , "Temp_5"      ),
        new Code(STATUS_TEMPERATURE_5      , "TMP.5"        , "Temp_6"      ),
        new Code(STATUS_TEMPERATURE_6      , "TMP.6"        , "Temp_7"      ),
        new Code(STATUS_TEMPERATURE_7      , "TMP.7"        , "Temp_8"      ),
        new Code(STATUS_TEMPERATURE_RANGE_0, "TMP.LIM.0"    , "TempRange_1" , true),
        new Code(STATUS_TEMPERATURE_RANGE_1, "TMP.LIM.1"    , "TempRange_2" , true),
        new Code(STATUS_TEMPERATURE_RANGE_2, "TMP.LIM.2"    , "TempRange_3" , true),
        new Code(STATUS_TEMPERATURE_RANGE_3, "TMP.LIM.3"    , "TempRange_4" , true),
        new Code(STATUS_TEMPERATURE_RANGE_4, "TMP.LIM.4"    , "TempRange_5" , true),
        new Code(STATUS_TEMPERATURE_RANGE_5, "TMP.LIM.5"    , "TempRange_6" , true),
        new Code(STATUS_TEMPERATURE_RANGE_6, "TMP.LIM.6"    , "TempRange_7" , true),
        new Code(STATUS_TEMPERATURE_RANGE_7, "TMP.LIM.7"    , "TempRange_8" , true),
        new Code(STATUS_TEMPERATURE        , "TMP.ALL"      , "Temp_All"    ),

        new Code(STATUS_LOGIN              , "LOGIN"        , "Login"       , true),
        new Code(STATUS_LOGOUT             , "LOGOUT"       , "Logout"      ),
        new Code(STATUS_CONNECT            , "CONNECT"      , "Connect"     ),
        new Code(STATUS_DISCONNECT         , "DISCONNECT"   , "Disconnect"  ),
        new Code(STATUS_ACK                , "ACK"          , "Ack"         ),
        new Code(STATUS_NAK                , "NAK"          , "Nak"         ),
        
        new Code(STATUS_OBC_FAULT          , "OBC.FAULT"    , "OBC_Fault"   , true),
        new Code(STATUS_OBC_RANGE          , "OBC.RANGE"    , "OBC_Range"   ),
        new Code(STATUS_OBC_RPM_RANGE      , "OBC.RPM"      , "OBC_Rpm"     ),
        new Code(STATUS_OBC_FUEL_RANGE     , "OBC.FUEL"     , "OBC_Fuel"    ),
        new Code(STATUS_OBC_OIL_RANGE      , "OBC.OIL"      , "OBC_Oil"     ),
        new Code(STATUS_OBC_TEMP_RANGE     , "OBC.TEMP"     , "OBC_Temp"    ),

        new Code(STATUS_LOW_BATTERY        , "LOWBATTERY"   , "LowBattery"  , true),

    };
    
    private static volatile HashMap<Object,Code> codeHashMap = null;
    private static HashMap<Object,Code> _GetCodeMap()
    {
        // create code table hashmap if necessary
        if (codeHashMap == null) {
            synchronized (_code) {
                if (codeHashMap == null) { // check again
                    HashMap<Object,Code> map = new HashMap<Object,Code>();
                    for (int i = 0; i < _code.length; i++) {
                        Integer keyCode = new Integer(_code[i].getCode());
                        map.put(keyCode, _code[i]);
                        String  keyName = _code[i].getName();
                        map.put(keyName, _code[i]);
                    }
                    // may have problems if the following isn't atomic, but the window
                    // is so small that I'm not going to worry about it now.
                    codeHashMap = map;
                }
            }
        }
        return codeHashMap;
    }
    
    public static Code GetCode(int code)
    {
        return (Code)_GetCodeMap().get(new Integer(code));
    }
    
    public static Code GetCode(String code)
    {
        if (code != null) {
            return (Code)_GetCodeMap().get(code);
        } else {
            return null;
        }
    }
    
    public static String GetCodeDescription(int code)
    {
        Code sc = GetCode(code);
        if (sc != null) {
            return sc.getDescription();
        } else {
            return "0x" + StringTools.toHexString((long)code,16);
        }
    }

    public static String GetCodeDescription(String codeKey)
    {
        Code sc = GetCode(codeKey);
        if (sc != null) {
            return sc.getDescription();
        } else {
            return codeKey;
        }
    }
    
    public static boolean IsHigherPriority(int code)
    {
        Code sc = GetCode(code);
        return (sc != null)? sc.isHigherPriority() : false;
    }

// ----------------------------------------------------------------------------

    public static void main(String argv[])
    {
        RTConfig.setCommandLineArgs(argv);
        int code = RTConfig.getInt("code",0);
        Print.logInfo("Code Description: " + StatusCodes.GetCodeDescription(code));
    }

}
