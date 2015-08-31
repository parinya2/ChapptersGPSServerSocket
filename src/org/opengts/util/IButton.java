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
//  2014/11/30  Martin D. Flynn
//     -Initial release
// ----------------------------------------------------------------------------
package org.opengts.util;

import java.lang.*;
import java.util.*;
import java.math.*;

/**
*** iButton container
**/

public class IButton
{

    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------

    /**
    *** DisplayFormat Enumeration
    **/
    public enum DisplayFormat implements EnumTools.StringLocale, EnumTools.IntValue {
        DECIMAL ( 0, I18N.getString(IButton.class,"IButton.DisplayFormat.decimal","Decimal (Little-Endian)")),
        HEX64   ( 1, I18N.getString(IButton.class,"IButton.DisplayFormat.hex64"  ,"Hex-64 (Big-Endian)"    )),
        HEX48   ( 2, I18N.getString(IButton.class,"IButton.DisplayFormat.hex48"  ,"Hex-48 (Big-Endian)"    ));
        // ---
        private int         vv = 0;
        private I18N.Text   aa = null;
        DisplayFormat(int v, I18N.Text a)           { vv = v; aa = a; }
        public int     getIntValue()                { return vv; }
        public String  toString()                   { return aa.toString(); }
        public String  toString(Locale loc)         { return aa.toString(loc); }
        public boolean isFormate(int fmt)           { return this.getIntValue() == fmt; }
    };

    /**
    *** Gets the DisplayFormat enumeration value from the specified name
    **/
    public static DisplayFormat getDisplayFormatFromName(String stn, DisplayFormat dft)
    {
        if (!StringTools.isBlank(stn)) {
            if (stn.equalsIgnoreCase("DEC"      )) { return DisplayFormat.DECIMAL;   } 
            if (stn.equalsIgnoreCase("DECIMAL"  )) { return DisplayFormat.DECIMAL;   } 
            if (stn.equalsIgnoreCase("HEX"      )) { return DisplayFormat.HEX64;     } 
            if (stn.equalsIgnoreCase("HEX64"    )) { return DisplayFormat.HEX64;     } 
            if (stn.equalsIgnoreCase("HEX48"    )) { return DisplayFormat.HEX48;     }
        }
        return dft;
    }

    /**
    *** Gets the String representation of the specified DisplayFormat.
    *** Returns "Unknown" id the specified DisplayFormat is null.
    **/
    public static String GetDisplayFormatDescription(DisplayFormat df, Locale locale)
    {
        if (df != null) {
            return df.toString(locale);
        } else {
            I18N i18n = I18N.getI18N(IButton.class, locale);
            return i18n.getString("IButton.DisplayFormat.unknown", "Unknown");
        }
    }

    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------

    /* value stored in 64-bit Little-Endian format as provided by device */
    private long valueLE = 0L; 

    /**
    *** Constructor.
    *** Values provided by iButton devices are in Little-Endian format.
    *** As such, these values are stored within this container in Little-Endian format.
    *** @param value  The 64-bit iButton value (in Little-Endian format)
    **/
    public IButton(long value)
    {
        // -- value assumed to be in Little-Endian format
        this(value, false/*Little-Endian*/);
    }

    /**
    *** Constructor.
    *** Values provided by iButton devices are in Little-Endian format.
    *** As such, these values are stored within this container in Little-Endian format.
    *** If the specified value will be in Big-Endian format, then the second parameter
    *** should specify "true" so that this specified value will be converted into
    *** the internal Little-Endian format.
    *** @param value      The 64-bit iButton value.
    *** @param bigEndian  True if the specified value is in Big-Endian format, false if Little-Endian.
    **/
    public IButton(long value, boolean bigEndian)
    {
        if (bigEndian) {
            // -- convert 64-bit Big-Endian to Little-Endian format
            this.valueLE = Payload.reverseByteOrder(value,8); // 64-bit
        } else {
            // -- save as provided Little-Endian format
            this.valueLE = value;
        }
    }

    /**
    *** Constructor.
    *** IButton values are typically provided by the device as a Little-Endian 64-bit integer.
    *** This value is printed on the physical iButton as a hex value with the LSB byte first.
    *** This constructor attempts to convert the specified String into a numeric value.
    *** The String is expected to be in one of the following forms:
    ***   1) A Hex value exactly 16 characters in length, as displayed on the physical iButton (in Big-Endian format)
    ***   2) A Decimal value 17..19 characters in length (in Little-Endian format)
    **/
    public IButton(String value)
    {
        String Vs = StringTools.trim(value);
        Print.sysPrintln("Parsing String: " + Vs);
        if ((Vs.length() == 12) && StringTools.isHex(Vs,true)) {
            // -- special case for a 48-bit stripped iButton value, convert to 64-bit with 00 header/footer
            // -  "0000179D3A2A" (Little-Endian) ==> 0x002A3A9D17000000 (Big-Endian)
            long V = StringTools.parseHexLong(Vs,0L);
            Print.sysPrintln("48-bit hex/decimal: " + V + " : 0x" + StringTools.toHexString(V,48));
            this.valueLE = Payload.reverseByteOrder(V,6) << 8; // convert to Little-Endian
        } else
        if (Vs.length() < 16) {
            // -- invalid hex/decimal
            this.valueLE = 0L;
        } else
        if (Vs.length() == 16) {
            if (StringTools.isHex(Vs,true/*strict*/)) {
                // -- parse as 64-bit hex
                long V = StringTools.parseHexLong(Vs,0L); // parsed as Big-Endian
                this.valueLE = Payload.reverseByteOrder(V,8); // convert to Little-Endian
            } else {
                // -- String contains non-hex characters
                this.valueLE = 0L;
            }
        } else 
        if (StringTools.isLong(Vs,true/*strict*/)) {
            // -- parse/retained as Little-Endian decimal
            this.valueLE = StringTools.parseLong(Vs,0L); // retained as Little-Endian
        } else {
            // -- String contains non-digit characters
            this.valueLE = 0L;
        }
    }

    // ------------------------------------------------------------------------

    /**
    *** Returns true if this instance contains a valie iButton tag value.
    *** (hi-order byte cannot be zero)
    **/
    public boolean isValid()
    {
        long V = this.getValue(); // Little-Endian
        return ((V & 0x00000000000000FFL) != 0L)? true : false; // LE hi-order byte
    }

    /**
    *** Gets the iButton tag value (Little-Endian format)
    **/
    public long getValue()
    {
        return this.valueLE;
    }

    /**
    *** Gets the iButton tag value
    *** @param bigEndian True to return as a Big-Endian value
    **/
    public long getValue(boolean bigEndian)
    {
        long V = this.getValue();
        if (bigEndian) {
            return Payload.reverseByteOrder(V,8);
        } else {
            return V;
        }
    }

    // ------------------------------------------------------------------------

    /**
    *** Converts the iButton tag value to a 64-bit decimal String (Little-Endian format)
    **/
    public String toDecimalString() 
    {
        return String.valueOf(this.getValue());
    }

    /**
    *** Converts the iButton tag value to a 64-bit hex String (Big-Endian format)
    **/
    public String toHexString() 
    {
        long BE = this.getValue(true/*Big-Endian*/);
        return StringTools.toHexString(BE,64);
    }

    /**
    *** Extract the middle 48-bits of the iButton tag value and returns it
    *** as a 48-bit hex String.
    **/
    public String toHexString48() 
    {
        String H = this.toHexString();
        if (H.length() == 16) {
            // -- expected length, extract middle 48-bit hex
            return H.substring(2,14);
        } else {
            // -- should not occur
            return H; // return as-is
        }
    }

    // ------------------------------------------------------------------------

    /**
    *** Returns the 64-bit hex String iButton tag value
    **/
    public String toString() 
    {
        return this.toHexString();
    }

    /**
    *** Returns the String representation of this iButton value in the specified
    *** DisplayFormat.  If the DisplayFormat is null, returns the 64-bit hex representation.
    **/
    public String toString(DisplayFormat df) 
    {
        if (df != null) {
            switch (df) {
                case HEX64   : // preferred
                    // -- 64-bit hex (Big-Endian): "310000179D3A2A01"
                    return this.toHexString();
                case HEX48   : // not recommended
                    // -- 48-bit hex (Big-Endian): "0000179D3A2A" (strip hi/lo bytes)
                    return this.toHexString48();
                case DECIMAL : 
                    // -- decimal (Little-Endian) as provided by device
                    return this.toDecimalString();
            }
        }
        return this.toHexString();
    }

    /** 
    *** Converts the specified iButton value into a String value
    *** @param value The iButton value
    *** @param st    The display format
    *** @return The value converted to the specified output format, or null if the 
    ***         specified display format is null.
    **/
    public static String toString(long value, DisplayFormat st)
    {
        return (new IButton(value)).toString(st);
    }

    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------

    public static void main(String argv[])
    {
        RTConfig.setCommandLineArgs(argv);
        IButton decBtn = new IButton(RTConfig.getLong(  "dec",0L));
        IButton strBtn = new IButton(RTConfig.getString("str",""));
        Print.sysPrintln("Decimal: " + decBtn.toString() + " : " + decBtn.getValue());
        Print.sysPrintln("String : " + strBtn.toString() + " : " + strBtn.getValue());
    }

}
