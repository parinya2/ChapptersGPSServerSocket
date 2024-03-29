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
// Change History:
//  2008/03/28  Martin D. Flynn
//     -Extracted from org.opendmtp.codes.ClientDiagnostics
// ----------------------------------------------------------------------------
package org.opengts.db.dmtp;

import java.lang.*;
import java.util.*;

import org.opengts.util.*;

public class ClientDiagnostics
{

    // ------------------------------------------------------------------------

    /**
    *** Gets the code description
    *** @param diagCode  The diagnostic code
    *** @return The diagnostic description
    **/
    public static String getDescription(int diagCode)
    {
        return getStringValue(diagCode, true, null, null);
    }

    /**
    *** Gets the String representation of the specified data for the specified diagnostic code
    *** @param diagCode  The diagnostic code
    *** @param diagData  The binary payload data
    *** @return The value String representation
    **/
    public static String getStringValue(int diagCode, byte diagData[])
    {
        return getStringValue(diagCode, false, diagData, null);
    }

    /**
    *** Gets the String representation of the specified data for the specified diagnostic code
    *** @param diagCode  The diagnostic code
    *** @param diagData  The binary payload data
    *** @param tz        A TimeZone used convert any dates encountered
    *** @return The value String representation
    **/
    public static String getStringValue(int diagCode, byte diagData[], TimeZone tz)
    {
        return getStringValue(diagCode, false, diagData, tz);
    }

    /**
    *** Gets the Description and/or converts the specified binary value to a String
    *** @param diagCode  The diagnostic code
    *** @param inclDesc  True to include the description, false to omit
    *** @param diagData  The binary payload data
    *** @return The value String representation
    **/
    public static String getStringValue(int diagCode, boolean inclDesc, byte diagData[])
    {
        return getStringValue(diagCode, inclDesc, diagData, null);
    }

    /**
    *** Gets the Description and/or converts the specified binary value to a String
    *** @param diagCode  The diagnostic code
    *** @param inclDesc  True to include the description, false to omit
    *** @param diagData  The binary payload data
    *** @param tz        A TimeZone used convert any dates encountered
    *** @return The Description and/or value String representation
    **/
    public static String getStringValue(int diagCode, boolean inclDesc, byte diagData[], TimeZone tz)
    {
        return "Chappters ClientDiagnostics getStringValue";
        
    }

    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------

    private ClientDiagnostics()
    {
        // not instantiated
    }

}
