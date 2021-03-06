/*!
 * @copyright
 * Copyright (c) 2019 Intel Corporation
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
 * @file construct_nvmf_subsystem_connector..cpp
 */

#include "spdk/spdk_api.hpp"
#include "nvmf_subsystem_create_connector.hpp"
#include "construct_nvmf_subsystem_connector.hpp"



namespace spdk {
constexpr const char ConstructNvmfSubsystemConnector::NQN[];
constexpr const char ConstructNvmfSubsystemConnector::INVALID_NQN[];


spdk::ConstructNvmfSubsystemConnector::ConstructNvmfSubsystemConnector() {



}

std::string ConstructNvmfSubsystemConnector::send_request(const std::string& message) {

    json::Json raw_request = json::Json::parse(message);


    auto id = raw_request["id"];

    json::Json params = raw_request["params"];

    if (raw_request["method"] ==  Method::GET_NVMF_SUBSYSTEMS) {
        std::vector<json::Json> response = {};
        response.push_back(nvmf_subsystem);

        return json_rpc::JsonRpcResponse(response, id).to_json().dump();
    }
    bool response = false;


    if (params.count(model::NvmfSubsystem::NQN) && params[model::NvmfSubsystem::NQN] == NQN) {


        nvmf_subsystem = params;

        nvmf_subsystem[model::NvmfSubsystem::SUBTYPE] = "NVMe";

        if (!params.count(model::NvmfSubsystem::LISTEN_ADDRESSES)) {
            std::vector<model::ListenAddress> listen_address{};
            nvmf_subsystem[model::NvmfSubsystem::LISTEN_ADDRESSES] = listen_address;
        }

        if (!params.count(model::NvmfSubsystem::HOSTS)) {
            std::vector<model::Host> hosts{};
            nvmf_subsystem[model::NvmfSubsystem::HOSTS] = hosts;
        }

        if (!params.count(model::NvmfSubsystem::NAMESPACES)) {
            std::vector<spdk::model::Namespace> namespaces{};
            nvmf_subsystem[model::NvmfSubsystem::NAMESPACES] = namespaces;
        }

        response = true;

    }

    return json_rpc::JsonRpcResponse(response, id).to_json().dump();
}

}