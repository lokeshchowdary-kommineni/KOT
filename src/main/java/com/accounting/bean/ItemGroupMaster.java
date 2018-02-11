/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author shinelogics
 */
@Entity
@Table(name = "item_group_master")
@XmlRootElement

public class ItemGroupMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_item")
    private Integer idItem;
    @Size(max = 50)
    @NotEmpty(message = "Please enter ItemGroup Name")
    @Column(name = "item_group_name")
    private String itemGroupName;
    @Column(name = "group_under")
    private String groupUnder;
   

    public ItemGroupMaster() {
    }

    public ItemGroupMaster(Integer idItem) {
        this.idItem = idItem;
    }

    public ItemGroupMaster(Integer idItem, Date createdOn, Date modifiedOn) {
        this.idItem = idItem;
      
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getItemGroupName() {
        return itemGroupName;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }

    public  String getGroupUnder() {
        return groupUnder;
    }

    public void setGroupUnder(String groupUnder) {
        this.groupUnder = groupUnder;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemGroupMaster)) {
            return false;
        }
        ItemGroupMaster other = (ItemGroupMaster) object;
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.ItemGroupMaster[ idItem=" + idItem + " ]";
    }
    
}
