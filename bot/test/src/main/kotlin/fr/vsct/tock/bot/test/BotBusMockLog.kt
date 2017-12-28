/*
 * Copyright (C) 2017 VSCT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.vsct.tock.bot.test

import fr.vsct.tock.bot.connector.ConnectorMessage
import fr.vsct.tock.bot.connector.ConnectorType
import fr.vsct.tock.bot.connector.ga.gaConnectorType
import fr.vsct.tock.bot.connector.messenger.messengerConnectorType
import fr.vsct.tock.bot.connector.slack.slackConnectorType
import fr.vsct.tock.bot.engine.action.Action
import fr.vsct.tock.bot.engine.action.SendSentence
import kotlin.test.assertEquals

/**
 * The actions sent by the mocked bus.
 */
data class BotBusMockLog(val action: Action, val delay: Long) {

    fun message(connectorType: ConnectorType): ConnectorMessage?
            = (action as? SendSentence)?.message(connectorType)

    fun messenger(): ConnectorMessage? = message(messengerConnectorType)

    fun ga(): ConnectorMessage? = message(gaConnectorType)

    fun slack(): ConnectorMessage? = message(slackConnectorType)

    fun text(): String? = (action as SendSentence).stringText

    fun assertText(text: String, message: String? = null)
            = assertEquals(text, text(), message)

    fun assertMessage(m: ConnectorMessage, message: String? = null)
            = assertEquals(m, message(m.connectorType), message)

}