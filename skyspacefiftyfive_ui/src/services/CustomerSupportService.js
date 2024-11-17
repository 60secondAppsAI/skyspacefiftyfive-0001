import http from "../http-common"; 

class CustomerSupportService {
  getAllCustomerSupports(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/customerSupport/customerSupports`, searchDTO);
  }

  get(customerSupportId) {
    return this.getRequest(`/customerSupport/${customerSupportId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/customerSupport?field=${matchData}`, null);
  }

  addCustomerSupport(data) {
    return http.post("/customerSupport/addCustomerSupport", data);
  }

  update(data) {
  	return http.post("/customerSupport/updateCustomerSupport", data);
  }
  
  uploadImage(data,customerSupportId) {
  	return http.postForm("/customerSupport/uploadImage/"+customerSupportId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new CustomerSupportService();
