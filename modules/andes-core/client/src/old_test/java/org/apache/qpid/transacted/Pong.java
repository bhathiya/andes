/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
package org.apache.qpid.transacted;

import org.apache.qpid.AMQException;
import org.apache.qpid.framing.AMQShortString;
import org.apache.qpid.exchange.ExchangeDefaults;
import org.wso2.andes.client.AMQQueue;

import javax.jms.Connection;
import javax.jms.JMSException;

public class Pong
{
    public static void main(String[] argv) throws Exception
    {
        Config config = new Config(argv);
        Connection con = config.createConnection();
        con.setClientID("pong");
        new Relay(new AMQQueue(ExchangeDefaults.DIRECT_EXCHANGE_NAME, new AMQShortString("pong")), new AMQQueue(ExchangeDefaults.DIRECT_EXCHANGE_NAME, new AMQShortString("ping")), con,
                  config.isEchoOn(),
                  config.getBatchSize(),
                  config.usePersistentMessages()).start();

    }
}
