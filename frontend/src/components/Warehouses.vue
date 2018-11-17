<template>
  <div>
    <div>
      <router-link to="/">Warehouses AGAIN</router-link>
    </div>
    <div>
      <router-link class="other-warehouses" to="/other-warehouses">Other Warehouses</router-link>
    </div>
    <div>{{this.query}}</div>
    <router-view></router-view>
    <ul class="warehouses">
      <li class="warehouse" v-for="warehouse in warehouses" :key="warehouse.id">
        {{warehouse.name}}
      </li>
    </ul>

    <md-dialog class="modal" :md-active.sync="showDialog">
          <md-dialog-title>Preferences</md-dialog-title>

          <md-dialog-content>
            <md-field>
              <label class="label" for="selectedValue">Test Select</label>
              <md-select class="select" v-model="selectedValue">
               <md-option v-for="(option, index) in filteredOptions" :key="index" :value="option" class="option">{{option}}</md-option>
              </md-select>
            </md-field>
          </md-dialog-content>

          <md-dialog-actions>
            <md-button class="md-primary" @click="close()">Close</md-button>
            <md-button class="md-primary" @click="save()">Save</md-button>
          </md-dialog-actions>
        </md-dialog>

    <md-button class="md-primary md-raised show-button" @click="showDialog = true">Show Dialog</md-button>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import _ from 'lodash'

export default {
  name: "Warehouses",
  computed: {
      query() {
        return window.location.pathname
      },
      filteredOptions: function() {
        if(_.isNull(this.lastSelectedValue)) {
          return this.options
        }

        return this.options.filter(option => option !== this.lastSelectedValue)
      },
    ...mapGetters({
      warehouses: 'getWarehouses'
    })
  },
  created() {
    this.$store.dispatch('FETCH_WAREHOUSES')
  },
  data: () => ({
        showDialog: false,
        selectedValue: null,
        lastSelectedValue: null,
        options: [
          'First',
          'Second',
          'Third'
        ]
      }),
  methods: {
    save: function() {
      this.showDialog = false
      console.log(this.selectedValue)
      this.lastSelectedValue = this.selectedValue
      this.selectedValue = null
    },
    close: function() {
      this.showDialog = false
      this.selectedValue = null
    }
  }
};
</script>

<style scoped>

</style>
