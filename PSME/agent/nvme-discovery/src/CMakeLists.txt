# <license_header>
#
# Copyright (c) 2017-2019 Intel Corporation
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# </license_header>

add_subdirectory(command)
add_subdirectory(loader)
add_subdirectory(discovery)
add_subdirectory(tools)
add_subdirectory(tree_stability)

generate_default_config_file(
    main.cpp
    agent
    generic
    "AGENT_NVME_CONFIGURATION_FILE"
    ../include/default_configuration.hpp
    ..
)

target_sources(psme-nvme-discovery PRIVATE
    src/main.cpp)