/*!
 * @brief Declaration of function processing Set Component Attributes command on Endpoint
 *
 * @copyright Copyright (c) 2017-2019 Intel Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @file set_endpoint_attributes.hpp
 */

#pragma once



#include "storage_agent_commands.hpp"



namespace agent {
namespace storage {
namespace command {

struct ConfigurableEndpointAttributes {
    OptionalField<std::string> username{};
    OptionalField<std::string> password{};
};

static const constexpr char EMPTY_VALUE[] = "";


/*!
 * @brief Implementation of process Endpoint
 * @param context Pointer to agent context
 * @param[in] uuid The uuid of the Endpoint
 * @param[in] attributes The attributes to be set
 * @param[out] response The response to SetComponentAttributes request
 * */
void process_endpoint_attributes(AgentContext::SPtr context,
                                 const Uuid& uuid,
                                 const agent_framework::model::attribute::Attributes& attributes,
                                 SetComponentAttributes::Response& response);

}
}
}