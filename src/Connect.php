<?php

namespace Rifeli\JobQueue;

use Disque\Connection\Credentials;
use Disque\Client;

$nodes = [
    new Credentials('127.0.0.1', 7711),
];

$disque = new Client($nodes);

$job = new \Disque\Queue\Job(['name' => 'Claudia']);
$disque->queue('my_queue')->push($job);

$queue = $disque->queue('my_queue');
while ($job = $queue->pull()) {
    echo "GOT JOB!";
    var_dump($job->getBody());
    $queue->processed($job);
}