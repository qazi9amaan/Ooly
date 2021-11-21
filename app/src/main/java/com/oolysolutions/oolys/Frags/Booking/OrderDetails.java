package com.oolysolutions.oolys.Frags.Booking;

import com.google.gson.annotations.SerializedName;

public class OrderDetails {
    private String assigned_by = null;
    private String delivery_state;
    private float id;
    private String order_id;
    private String reference_id;
    private String tracking_id;
    private String order_status;
    private String order_status_reason = null;
    private String ordered_at;
    private String delivered_on = null;
    private String request_time;
    private float senders_id;
    private float recievers_id;
    private String delivery_item_type;
    private String cust_delivery_plan = null;
    private String fragile_delivery_item;
    private String reason = null;
    private String driver_assigned = null;
    private String received_buy = null;
    private String recieved_amount;
    @SerializedName("order-type")
    private String order_type;
    private String quantity;
    private float cod_recieved_driver;
    private float cod_recieved_customer;
    private String order_specs = null;
    private String product_price = null;
    private String delivery_type = null;
    private float profit_recieved_tag;
    private float salary_released_tag;
    private String paymentDetails;
    private String consumerId;
    private String shippmentType;
    private float cust_id;
    private String cust_full_name;
    private String cust_phone_number;
    private String cust_email;
    private String cust_district;
    private String cust_address;
    private String cust_area;
    private String pincode;
    private String cust_lat;
    private String cust_lon;
    private String state;
    private String cust_address_id;
    private String parent;
    private float cust_drop_id;
    private String reciever_name;
    private String reciever_phone_number;
    private String reciever_district;
    private String reciever_address;
    private String delivery_area;
    private String delivery_location = null;
    private String rec_pincode;
    private float rec_lat;
    private String rec_lon;
    private String rec_state;
    private String rec_address_id;
    private String rec_parent;
    private String driver_id = null;
    private String created_at = null;
    private String phone = null;
    private String full_name = null;
    private String alternate_phone = null;
    private String email_address = null;
    private String district = null;
    private String date_of_birth = null;
    private String landmark = null;
    private String area = null;
    private String address = null;
    private String password = null;
    private String adhaar_card = null;
    private String driving_liscene = null;
    private String profile_pic = null;
    private String series_id = null;
    private String remember_token = null;
    private String expires = null;
    private String reffered_by = null;
    private float pmt_id;
    private String charges;
    private String final_cost;
    private String kms;
    private String pay_at_pickup;

    public String getAssigned_by() {
        return assigned_by;
    }

    public void setAssigned_by(String assigned_by) {
        this.assigned_by = assigned_by;
    }

    public String getDelivery_state() {
        return delivery_state;
    }

    public void setDelivery_state(String delivery_state) {
        this.delivery_state = delivery_state;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getReference_id() {
        return reference_id;
    }

    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    public String getTracking_id() {
        return tracking_id;
    }

    public void setTracking_id(String tracking_id) {
        this.tracking_id = tracking_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_status_reason() {
        return order_status_reason;
    }

    public void setOrder_status_reason(String order_status_reason) {
        this.order_status_reason = order_status_reason;
    }

    public String getOrdered_at() {
        return ordered_at;
    }

    public void setOrdered_at(String ordered_at) {
        this.ordered_at = ordered_at;
    }

    public String getDelivered_on() {
        return delivered_on;
    }

    public void setDelivered_on(String delivered_on) {
        this.delivered_on = delivered_on;
    }

    public String getRequest_time() {
        return request_time;
    }

    public void setRequest_time(String request_time) {
        this.request_time = request_time;
    }

    public float getSenders_id() {
        return senders_id;
    }

    public void setSenders_id(float senders_id) {
        this.senders_id = senders_id;
    }

    public float getRecievers_id() {
        return recievers_id;
    }

    public void setRecievers_id(float recievers_id) {
        this.recievers_id = recievers_id;
    }

    public String getDelivery_item_type() {
        return delivery_item_type;
    }

    public void setDelivery_item_type(String delivery_item_type) {
        this.delivery_item_type = delivery_item_type;
    }

    public String getCust_delivery_plan() {
        return cust_delivery_plan;
    }

    public void setCust_delivery_plan(String cust_delivery_plan) {
        this.cust_delivery_plan = cust_delivery_plan;
    }

    public String getFragile_delivery_item() {
        return fragile_delivery_item;
    }

    public void setFragile_delivery_item(String fragile_delivery_item) {
        this.fragile_delivery_item = fragile_delivery_item;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDriver_assigned() {
        return driver_assigned;
    }

    public void setDriver_assigned(String driver_assigned) {
        this.driver_assigned = driver_assigned;
    }

    public String getReceived_buy() {
        return received_buy;
    }

    public void setReceived_buy(String received_buy) {
        this.received_buy = received_buy;
    }

    public String getRecieved_amount() {
        return recieved_amount;
    }

    public void setRecieved_amount(String recieved_amount) {
        this.recieved_amount = recieved_amount;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public float getCod_recieved_driver() {
        return cod_recieved_driver;
    }

    public void setCod_recieved_driver(float cod_recieved_driver) {
        this.cod_recieved_driver = cod_recieved_driver;
    }

    public float getCod_recieved_customer() {
        return cod_recieved_customer;
    }

    public void setCod_recieved_customer(float cod_recieved_customer) {
        this.cod_recieved_customer = cod_recieved_customer;
    }

    public String getOrder_specs() {
        return order_specs;
    }

    public void setOrder_specs(String order_specs) {
        this.order_specs = order_specs;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
    }

    public float getProfit_recieved_tag() {
        return profit_recieved_tag;
    }

    public void setProfit_recieved_tag(float profit_recieved_tag) {
        this.profit_recieved_tag = profit_recieved_tag;
    }

    public float getSalary_released_tag() {
        return salary_released_tag;
    }

    public void setSalary_released_tag(float salary_released_tag) {
        this.salary_released_tag = salary_released_tag;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getShippmentType() {
        return shippmentType;
    }

    public void setShippmentType(String shippmentType) {
        this.shippmentType = shippmentType;
    }

    public float getCust_id() {
        return cust_id;
    }

    public void setCust_id(float cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_full_name() {
        return cust_full_name;
    }

    public void setCust_full_name(String cust_full_name) {
        this.cust_full_name = cust_full_name;
    }

    public String getCust_phone_number() {
        return cust_phone_number;
    }

    public void setCust_phone_number(String cust_phone_number) {
        this.cust_phone_number = cust_phone_number;
    }

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    public String getCust_district() {
        return cust_district;
    }

    public void setCust_district(String cust_district) {
        this.cust_district = cust_district;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public String getCust_area() {
        return cust_area;
    }

    public void setCust_area(String cust_area) {
        this.cust_area = cust_area;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCust_lat() {
        return cust_lat;
    }

    public void setCust_lat(String cust_lat) {
        this.cust_lat = cust_lat;
    }

    public String getCust_lon() {
        return cust_lon;
    }

    public void setCust_lon(String cust_lon) {
        this.cust_lon = cust_lon;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCust_address_id() {
        return cust_address_id;
    }

    public void setCust_address_id(String cust_address_id) {
        this.cust_address_id = cust_address_id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public float getCust_drop_id() {
        return cust_drop_id;
    }

    public void setCust_drop_id(float cust_drop_id) {
        this.cust_drop_id = cust_drop_id;
    }

    public String getReciever_name() {
        return reciever_name;
    }

    public void setReciever_name(String reciever_name) {
        this.reciever_name = reciever_name;
    }

    public String getReciever_phone_number() {
        return reciever_phone_number;
    }

    public void setReciever_phone_number(String reciever_phone_number) {
        this.reciever_phone_number = reciever_phone_number;
    }

    public String getReciever_district() {
        return reciever_district;
    }

    public void setReciever_district(String reciever_district) {
        this.reciever_district = reciever_district;
    }

    public String getReciever_address() {
        return reciever_address;
    }

    public void setReciever_address(String reciever_address) {
        this.reciever_address = reciever_address;
    }

    public String getDelivery_area() {
        return delivery_area;
    }

    public void setDelivery_area(String delivery_area) {
        this.delivery_area = delivery_area;
    }

    public String getDelivery_location() {
        return delivery_location;
    }

    public void setDelivery_location(String delivery_location) {
        this.delivery_location = delivery_location;
    }

    public String getRec_pincode() {
        return rec_pincode;
    }

    public void setRec_pincode(String rec_pincode) {
        this.rec_pincode = rec_pincode;
    }

    public float getRec_lat() {
        return rec_lat;
    }

    public void setRec_lat(float rec_lat) {
        this.rec_lat = rec_lat;
    }

    public String getRec_lon() {
        return rec_lon;
    }

    public void setRec_lon(String rec_lon) {
        this.rec_lon = rec_lon;
    }

    public String getRec_state() {
        return rec_state;
    }

    public void setRec_state(String rec_state) {
        this.rec_state = rec_state;
    }

    public String getRec_address_id() {
        return rec_address_id;
    }

    public void setRec_address_id(String rec_address_id) {
        this.rec_address_id = rec_address_id;
    }

    public String getRec_parent() {
        return rec_parent;
    }

    public void setRec_parent(String rec_parent) {
        this.rec_parent = rec_parent;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAlternate_phone() {
        return alternate_phone;
    }

    public void setAlternate_phone(String alternate_phone) {
        this.alternate_phone = alternate_phone;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdhaar_card() {
        return adhaar_card;
    }

    public void setAdhaar_card(String adhaar_card) {
        this.adhaar_card = adhaar_card;
    }

    public String getDriving_liscene() {
        return driving_liscene;
    }

    public void setDriving_liscene(String driving_liscene) {
        this.driving_liscene = driving_liscene;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getSeries_id() {
        return series_id;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getReffered_by() {
        return reffered_by;
    }

    public void setReffered_by(String reffered_by) {
        this.reffered_by = reffered_by;
    }

    public float getPmt_id() {
        return pmt_id;
    }

    public void setPmt_id(float pmt_id) {
        this.pmt_id = pmt_id;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getFinal_cost() {
        return final_cost;
    }

    public void setFinal_cost(String final_cost) {
        this.final_cost = final_cost;
    }

    public String getKms() {
        return kms;
    }

    public void setKms(String kms) {
        this.kms = kms;
    }

    public String getPay_at_pickup() {
        return pay_at_pickup;
    }

    public void setPay_at_pickup(String pay_at_pickup) {
        this.pay_at_pickup = pay_at_pickup;
    }
}
