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
            <customerSupport-table
            v-if="customerSupports && customerSupports.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:customerSupports="customerSupports"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-customer-supports="getAllCustomerSupports"
             >

            </customerSupport-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CustomerSupportTable from "@/components/CustomerSupportTable";
import CustomerSupportService from "../services/CustomerSupportService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CustomerSupportTable,
  },
  data() {
    return {
      customerSupports: [],
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
    async getAllCustomerSupports(sortBy='customerSupportId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CustomerSupportService.getAllCustomerSupports(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.customerSupports.length) {
					this.customerSupports = response.data.customerSupports;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching customerSupports:", error);
        }
        
      } catch (error) {
        console.error("Error fetching customerSupport details:", error);
      }
    },
  },
  mounted() {
    this.getAllCustomerSupports();
  },
  created() {
    this.$root.$on('searchQueryForCustomerSupportsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCustomerSupports();
    })
  }
};
</script>
<style></style>
