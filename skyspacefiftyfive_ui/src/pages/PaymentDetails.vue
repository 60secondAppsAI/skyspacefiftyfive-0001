<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <paymentDetail-table
            v-if="paymentDetails && paymentDetails.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:paymentDetails="paymentDetails"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-payment-details="getAllPaymentDetails"
             >

            </paymentDetail-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PaymentDetailTable from "@/components/PaymentDetailTable";
import PaymentDetailService from "../services/PaymentDetailService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PaymentDetailTable,
  },
  data() {
    return {
      paymentDetails: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllPaymentDetails(sortBy='paymentDetailId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PaymentDetailService.getAllPaymentDetails(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.paymentDetails.length) {
					this.paymentDetails = response.data.paymentDetails;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching paymentDetails:", error);
        }
        
      } catch (error) {
        console.error("Error fetching paymentDetail details:", error);
      }
    },
  },
  mounted() {
    this.getAllPaymentDetails();
  },
  created() {
    this.$root.$on('searchQueryForPaymentDetailsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPaymentDetails();
    })
  }
};
</script>
<style></style>
