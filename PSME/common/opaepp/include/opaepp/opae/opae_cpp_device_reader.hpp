/*!
 * @brief OPAE device reader interface
 *
 * @copyright Copyright (c) 2019 Intel Corporation
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
 * @file opae_cpp_device_reader.hpp
 */

#pragma once



#include "opaepp/device_reader.hpp"
#include "opae/cxx/core/token.h"



namespace opaepp {

class OpaeCppDeviceReader : public DeviceReader {

public:

    /*!
     * @brief constructor - reads all OPAE devices
     * logs if discovery of any particular device failed
     * @throws runtime_error if it was unable to get tokens for discovery of devices
     * */
    OpaeCppDeviceReader();


    /*!
     * @brief Reads all OPAE devices, logs if discovery of any particular device failed.
     * @throws runtime_error if it was unable to get tokens for discovery of devices
     * @return reference to DeviceReader object
     */
    DeviceReader& read_devices();


    /*!
     * @brief Reads OPAE device for given BDF, logs if discovery of any particular device failed.
     * @param pcie_device_address pcie device address
     * @throws runtime_error if it was unable to get tokens for discovery of devices
     * @return reference to DeviceReader object
     */
    DeviceReader& read_devices(const PcieDeviceAddress& pcie_device_address);


private:
    /*!
     * @brief Performs discovery of Device object based on given token
     * @param token_ptr pointer to OPAE token
     * @return Device object with properties discovered
     */
    Device discover(const opae::fpga::types::token::ptr_t& token_ptr);


    /*!
     * @brief Reads properties and stores it in given device object
     * @param device device object to be filled with properties
     * @param token_ptr pointer to OPAE token which is used to get device properties
     */
    void read_properties(Device& device, const opae::fpga::types::token::ptr_t& token_ptr);
};

}
