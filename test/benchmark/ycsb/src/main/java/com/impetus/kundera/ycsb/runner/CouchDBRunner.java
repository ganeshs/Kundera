/**
 * Copyright 2012 Impetus Infotech.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.impetus.kundera.ycsb.runner;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.Configuration;

import com.impetus.kundera.ycsb.utils.CouchDBOperationUtils;
import com.impetus.kundera.ycsb.utils.MailUtils;
import common.Logger;

/**
 * @author Vivek Mishra
 * 
 */
public class CouchDBRunner extends YCSBRunner
{
    private CouchDBOperationUtils operationUtils;

    private static Logger logger = Logger.getLogger(CouchDBRunner.class);

    public CouchDBRunner(final String propertyFile, final Configuration config)
    {
        super(propertyFile,config);
        operationUtils = new CouchDBOperationUtils();
    }

    @Override
    public void startServer(boolean performDelete, Runtime runTime)
    {
        try
        {
            operationUtils.createdatabase(schema, host, port);
        }
        catch (Exception e)
        {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stopServer(Runtime runTime)
    {
    	// No need to run with "t" option.
    	
        try
        {
//            operationUtils.dropDatabase(schema, host, port);
        }
        catch (Exception e)
        {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    protected void sendMail()
    {
        Map<String, Double> delta = new HashMap<String, Double>();
        double kunderaPelopsToPelopsDelta = 0.0;
        double kunderaThriftToThriftDelta = 0.0;
        if (timeTakenByClient.get(clients[1]) != null && timeTakenByClient.get(clients[0]) != null)
        {

            kunderaPelopsToPelopsDelta = ((timeTakenByClient.get(clients[1]).doubleValue() - timeTakenByClient.get(
                    clients[0]).doubleValue())
                    / timeTakenByClient.get(clients[1]).doubleValue() * 100);
        }

        if (clients.length ==3 && timeTakenByClient.get(clients[2]) != null && timeTakenByClient.get(clients[3]) != null)
        {
            kunderaThriftToThriftDelta = ((timeTakenByClient.get(clients[3]).doubleValue() - timeTakenByClient.get(
                    clients[2]).doubleValue())
                    / timeTakenByClient.get(clients[3]).doubleValue() * 100);
        }
        // double kunderaPelopsToHectorDelta =
        // ((timeTakenByClient.get(clients[1]) -
        // timeTakenByClient.get(clients[4]))
        // / timeTakenByClient.get(clients[1]) * 100);
        delta.put("kunderaPelopsToPelopsDelta", kunderaPelopsToPelopsDelta);
        delta.put("kunderaThriftToThriftDelta", kunderaThriftToThriftDelta);
        // delta.put("kunderaPelopsToHectorDelta", kunderaPelopsToHectorDelta);

        if ( /*(kunderaPelopsToHectorDelta > 10.00) || */(kunderaPelopsToPelopsDelta > 8.00)
                || (kunderaThriftToThriftDelta > 8.00))
        {
            MailUtils.sendMail(delta, isUpdate ? "update" : runType, "cassandra");
        } else
        {
            MailUtils.sendPositiveEmail(delta, isUpdate ? "update" : runType, "cassandra");
            
        }

    }
}
