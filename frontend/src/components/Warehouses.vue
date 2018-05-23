<template>
  <ul class="warehouses">
    <li class="warehouse" v-for="warehouse in warehouses" :key="warehouse.id">
      {{warehouse.name}}
    </li>
  </ul>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: "Warehouses",
  computed: mapGetters({
    warehouses: 'getWarehouses'
  }),
  created() {
    this.$store.dispatch('FETCH_WAREHOUSES')
  }
};
</script>

<style scoped>

</style>
