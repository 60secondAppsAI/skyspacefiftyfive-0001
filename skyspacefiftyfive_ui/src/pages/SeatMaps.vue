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
            <seatMap-table
            v-if="seatMaps && seatMaps.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:seatMaps="seatMaps"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-seat-maps="getAllSeatMaps"
             >

            </seatMap-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SeatMapTable from "@/components/SeatMapTable";
import SeatMapService from "../services/SeatMapService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SeatMapTable,
  },
  data() {
    return {
      seatMaps: [],
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
    async getAllSeatMaps(sortBy='seatMapId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SeatMapService.getAllSeatMaps(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.seatMaps.length) {
					this.seatMaps = response.data.seatMaps;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching seatMaps:", error);
        }
        
      } catch (error) {
        console.error("Error fetching seatMap details:", error);
      }
    },
  },
  mounted() {
    this.getAllSeatMaps();
  },
  created() {
    this.$root.$on('searchQueryForSeatMapsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSeatMaps();
    })
  }
};
</script>
<style></style>
