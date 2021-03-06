"""
 * @section LICENSE
 *
 * @copyright
 * Copyright (c) 2019 Intel Corporation
 *
 * @copyright
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * @copyright
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * @copyright
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @section DESCRIPTION
"""

from fpgaof_actions.action import Action
from fpgaof_helpers.build_information import BuildInformation
from fpgaof_helpers.constants import Constants


class ShowVersionAction(Action):
    ACTION = 'version'
    PARAM_NAME = 'ACTION'

    def process_action(self, configuration):
        print('%s %s' % (Constants.VERSION_TEXT_FPGAoF_WHEEL,
                         BuildInformation.BUILD_INFORMATION))
