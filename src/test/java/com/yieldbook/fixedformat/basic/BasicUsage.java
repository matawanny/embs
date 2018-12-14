/*
 * Copyright 2004 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yieldbook.fixedformat.basic;

import com.ancientprogramming.fixedformat4j.format.FixedFormatManager;
import com.ancientprogramming.fixedformat4j.format.impl.FixedFormatManagerImpl;
import com.yieldbook.mortgage.domain.gnma.GnmaLoan;
import com.yieldbook.mortgage.domain.gnma.GnmaLoanHeader;

/**
 * Shows the basic usage
 *
 * @author Jacob von Eyben - http://www.ancientprogramming.com
 * @since 1.2.0
 */
//START-SNIPPET: basicusage
public class BasicUsage {

  private static FixedFormatManager manager = new FixedFormatManagerImpl();

  public static void main(String[] args) {
    String string = "string    001232008-05-29";
    BasicRecord record = manager.load(BasicRecord.class, string);

    System.out.println("The parsed string: " + record.getStringData());
    System.out.println("The parsed integer: " + record.getIntegerData());
    System.out.println("The parsed date: " + record.getDateData());

    record.setIntegerData(100);
    System.out.println("Exported: " + manager.export(record));
    
    String input="P3617G0TR1BE5960CSF201808014255201808";
    GnmaLoanHeader pool = manager.load(GnmaLoanHeader.class, input);
    System.out.println(pool);
    
    input="LBE596015108932724255F1 2018080120480701050000000810000000008100000           36000135900    097611035903754650NN01750008501N2OH     2N 20180820180629";
    GnmaLoan loan = manager.load(GnmaLoan.class, input);
    System.out.println(loan);
  }
}
//END-SNIPPET: basicusage
