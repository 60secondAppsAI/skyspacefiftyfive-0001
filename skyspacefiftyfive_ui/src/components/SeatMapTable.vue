
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Seat Maps</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalSeatMaps = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalSeatMaps">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add SeatMap</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="SeatMapId" type="text" placeholder="Enter SeatMapId" v-model="seatMapToAdd.seatMapId"></base-input>
  <base-input label="Configuration" type="text" placeholder="Enter Configuration" v-model="seatMapToAdd.configuration"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="seatMaps" :row-key="record => record.SeatMapId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <SeatMapPictureView :seatMaps="seatMaps" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import SeatMapService from "../services/SeatMapService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import SeatMapPictureView from './SeatMapPictureView.vue';


const seatMapsColumns = [
  "seatMapId",
  "year",
  "date",
  "competitionId",
  "seatMapId"
]

export default {
  props: {
    seatMaps: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    SeatMapPictureView
  },

  data() {
    return {
      modalSeatMaps: false,
        isTableView: true,

      columns: [
        {
          title: 'Seat Map Id',
		dataIndex: 'seatMapId',
          visible: true,
          scopedSlots: { customRender: 'seatMapId' },
          sorter: true
          //sorter: (a, b) => a.seatMapId - b.seatMapId,
          //sorter: (a, b) => a.seatMapId.localeCompare(b.seatMapId),
        },
        {
          title: 'Configuration',
		dataIndex: 'configuration',
          visible: true,
          scopedSlots: { customRender: 'configuration' },
          sorter: true
          //sorter: (a, b) => a.configuration - b.configuration,
          //sorter: (a, b) => a.configuration.localeCompare(b.configuration),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} seatMaps`,
      },

      seatMaps: [],
      seatMapToAdd: {},

      seatMapsTable: {
        columns: [...seatMapsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'seatMapId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderSeatMapsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let seatMapsTableData = [];
      for (let i = 0; i < this.seatMaps.length; i++) {
        seatMapsTableData.push({
          id: i,
          seatMapId: this.seatMaps[i].seatMapId,
          year: this.seatMaps[i].year,
          date: this.seatMaps[i].date,
          competitionId: this.seatMaps[i].competitionId,
          seatMapId: this.seatMaps[i].seatMapId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-seat-maps',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToAirlineDetail(id) {
      this.$router.push({ name: 'AirlineDetail', params: { airlineId: id.toString() }})
    },
    routingToFlightDetail(id) {
      this.$router.push({ name: 'FlightDetail', params: { flightId: id.toString() }})
    },
    routingToPassengerDetail(id) {
      this.$router.push({ name: 'PassengerDetail', params: { passengerId: id.toString() }})
    },
    routingToBookingDetail(id) {
      this.$router.push({ name: 'BookingDetail', params: { bookingId: id.toString() }})
    },
    routingToTicketDetail(id) {
      this.$router.push({ name: 'TicketDetail', params: { ticketId: id.toString() }})
    },
    routingToAircraftDetail(id) {
      this.$router.push({ name: 'AircraftDetail', params: { aircraftId: id.toString() }})
    },
    routingToCrewMemberDetail(id) {
      this.$router.push({ name: 'CrewMemberDetail', params: { crewMemberId: id.toString() }})
    },
    routingToAirportDetail(id) {
      this.$router.push({ name: 'AirportDetail', params: { airportId: id.toString() }})
    },
    routingToLuggageDetail(id) {
      this.$router.push({ name: 'LuggageDetail', params: { luggageId: id.toString() }})
    },
    routingToPaymentDetailDetail(id) {
      this.$router.push({ name: 'PaymentDetailDetail', params: { paymentDetailId: id.toString() }})
    },
    routingToLoyaltyProgramDetail(id) {
      this.$router.push({ name: 'LoyaltyProgramDetail', params: { loyaltyProgramId: id.toString() }})
    },
    routingToPromotionDetail(id) {
      this.$router.push({ name: 'PromotionDetail', params: { promotionId: id.toString() }})
    },
    routingToSeatMapDetail(id) {
      this.$router.push({ name: 'SeatMapDetail', params: { seatMapId: id.toString() }})
    },
    routingToMealOptionDetail(id) {
      this.$router.push({ name: 'MealOptionDetail', params: { mealOptionId: id.toString() }})
    },
    routingToTravelClassDetail(id) {
      this.$router.push({ name: 'TravelClassDetail', params: { travelClassId: id.toString() }})
    },
    routingToCheckinDetailDetail(id) {
      this.$router.push({ name: 'CheckinDetailDetail', params: { checkinDetailId: id.toString() }})
    },
    routingToBoardingPassDetail(id) {
      this.$router.push({ name: 'BoardingPassDetail', params: { boardingPassId: id.toString() }})
    },
    routingToLoungeAccessDetail(id) {
      this.$router.push({ name: 'LoungeAccessDetail', params: { loungeAccessId: id.toString() }})
    },
    routingToWeatherAlertDetail(id) {
      this.$router.push({ name: 'WeatherAlertDetail', params: { weatherAlertId: id.toString() }})
    },
    routingToCustomerSupportDetail(id) {
      this.$router.push({ name: 'CustomerSupportDetail', params: { customerSupportId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForSeatMapsChanged', this.searchQuery);
		//this.renderSeatMapsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalSeatMaps = false;

      const currentDate = new Date().getTime();
      this.seatMapToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.seatMapToAdd);
      console.log(jsonData);
      
      const res = await SeatMapService.addSeatMap(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderSeatMapsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>
