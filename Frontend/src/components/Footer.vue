<template>
  <div class="container">
    <!-- Moustache avec description -->
    <p class="footer" v-once>{{ description }}</p>
  </div>
</template>

<script>
export default {
  name: "Footer",
  data() {
    return {
      description:
        "Projet : Machine de vote."
    };
  }
};
</script>

<style>
.footer {
  font-size: 15px;
  color: #6c757d;
  text-decoration: underline;
  text-decoration-color: yellow;
  text-align: right;
  margin-top: 4em;
  margin-right: 2em;
}
</style>