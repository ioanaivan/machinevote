<template>
  <div>
    <router-view/>
    <enregistrementVoteMachine/>
    <footer/>
  </div>
</template>

<script>
// import enregistrementVoteMachine from "@/components/EnregistrementVoteMachine.vue";
import footer from "@/components/Footer.vue";
// import reponseEnregistrement from "@/components/ReponseEnregistrement.vue";

export default {
  name: "app",
  components: { footer }
};
</script>

<style>
</style>
