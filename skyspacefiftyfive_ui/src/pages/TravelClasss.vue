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
            <travelClass-table
            v-if="travelClasss && travelClasss.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:travelClasss="travelClasss"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-travel-classs="getAllTravelClasss"
             >

            </travelClass-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import TravelClassTable from "@/components/TravelClassTable";
import TravelClassService from "../services/TravelClassService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    TravelClassTable,
  },
  data() {
    return {
      travelClasss: [],
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
    async getAllTravelClasss(sortBy='travelClassId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await TravelClassService.getAllTravelClasss(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.travelClasss.length) {
					this.travelClasss = response.data.travelClasss;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching travelClasss:", error);
        }
        
      } catch (error) {
        console.error("Error fetching travelClass details:", error);
      }
    },
  },
  mounted() {
    this.getAllTravelClasss();
  },
  created() {
    this.$root.$on('searchQueryForTravelClasssChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllTravelClasss();
    })
  }
};
</script>
<style></style>
