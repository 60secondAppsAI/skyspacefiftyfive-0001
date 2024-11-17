import http from "../http-common"; 

class PaymentDetailService {
  getAllPaymentDetails(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/paymentDetail/paymentDetails`, searchDTO);
  }

  get(paymentDetailId) {
    return this.getRequest(`/paymentDetail/${paymentDetailId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/paymentDetail?field=${matchData}`, null);
  }

  addPaymentDetail(data) {
    return http.post("/paymentDetail/addPaymentDetail", data);
  }

  update(data) {
  	return http.post("/paymentDetail/updatePaymentDetail", data);
  }
  
  uploadImage(data,paymentDetailId) {
  	return http.postForm("/paymentDetail/uploadImage/"+paymentDetailId, data);
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

export default new PaymentDetailService();
