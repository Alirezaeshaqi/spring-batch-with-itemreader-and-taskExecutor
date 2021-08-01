/*
 * Copyright 2016 the original author or authors.
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
package io.spring.batch.proccessor;

import io.spring.batch.domain.*;
import io.spring.batch.services.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Michael Minella
 */
@Component
public class FilteringItemProcessor implements ItemProcessor<Customer, ItemWriterDto> {

    @Autowired
    CustomerService customerService;

    @Autowired
    Sequences sequences;


    public static Map<String, Integer> branchMap;

    @Override
    public ItemWriterDto process(Customer item) throws Exception {
        try {
            System.out.println("national_code is equal to " + item.getNationalCode() + " time : " + new Date());
            ItemWriterDto itemWriterDto = new ItemWriterDto();
            Customer customer;
            customer = customerService.fetchCustomerByNationalCode(item.getNationalCode());

            if (customer == null) {

                Customer innerCustomer = new Customer();
                System.out.println("operation : insert" + item.getNationalCode() + " time : " + new Date());
                System.out.println("------------------------------------------------------------------------");
                innerCustomer.setCustomerType(item.getCustomerType());
                innerCustomer.setCustomerState((short) 2);
                innerCustomer.setNationalCode(item.getNationalCode());
                itemWriterDto.setCustomer(customer);

                /*part1----customer doesnt exist*/

            } else if (customer != null) {
                try {

                    /*part2----customer exist*/

                    Customer innerCustomer = new Customer();
                    System.out.println("operation : update" + item.getNationalCode() + " time : " + new Date());
                    System.out.println("------------------------------------------------------------------------");
                    innerCustomer.setCustomerType(item.getCustomerType());
                    innerCustomer.setCustomerState((short) 2);
                    innerCustomer.setNationalCode(item.getNationalCode());
                    itemWriterDto.setCustomer(customer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            /*finally we need to return itemWriter*/
            return itemWriterDto;
        } catch (Exception e) {
            item.toString();
            e.printStackTrace();
            return null;
        }
    }

}