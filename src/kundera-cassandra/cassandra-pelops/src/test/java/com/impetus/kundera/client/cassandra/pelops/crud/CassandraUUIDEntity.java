/*******************************************************************************
 * * Copyright 2012 Impetus Infotech.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 ******************************************************************************/
package com.impetus.kundera.client.cassandra.pelops.crud;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vivek.mishra
 * 
 */
@Entity
@Table(name = "uuidsample", schema = "UUIDCassandra@cass_pu")
public class CassandraUUIDEntity
{
    @Id
    @Column(name = "uuidKey")
    private UUID uuidKey;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    /**
     * Default constructor.
     */
    public CassandraUUIDEntity()
    {

    }

    /**
     * @return the uuidKey
     */
    public UUID getUuidKey()
    {
        return uuidKey;
    }

    /**
     * @param uuidKey
     *            the uuidKey to set
     */
    public void setUuidKey(UUID uuidKey)
    {
        this.uuidKey = uuidKey;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the age
     */
    public Integer getAge()
    {
        return age;
    }

    /**
     * @param age
     *            the age to set
     */
    public void setAge(Integer age)
    {
        this.age = age;
    }

}
